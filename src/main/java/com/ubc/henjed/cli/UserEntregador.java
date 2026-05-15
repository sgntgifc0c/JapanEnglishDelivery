package com.ubc.henjed.cli;

import com.ubc.henjed.model.Entregador;
import com.ubc.henjed.model.ItemPedido;
import com.ubc.henjed.model.Pedido;
import com.ubc.henjed.model.Veiculo;
import com.ubc.henjed.util.CMD;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class UserEntregador implements IUsuario {

    Entregador entregador;
    Scanner scan;
    Connection conn;

    public UserEntregador(Entregador cliente, Scanner scan, Connection conn) {
        this.entregador = cliente;
        this.scan = scan;
        this.conn = conn;
    }

    public void exibirMenu() {
        CMD.msg("1 - Acessar Pedido para entrega");
        CMD.msg("2 - Histórico de pedidos sobre sua responsabilidade");
        CMD.msg("3 - Adicionar Veiculo");
        CMD.msg("4 - Listar Veiculos");
        CMD.msg("5 - Exibir suas informações");
        CMD.msg("6 - Editar suas informações");
        CMD.msg("7 - Sair");
    }

    public boolean selecao() throws SQLException, Exception {
        var sair = false;

        switch (scan.nextInt()) {
            case 1:
                acessarTarefa();
                break;
            case 2:
                var listaPedidos = new Pedido().getCollection(
                    conn,
                    "WHERE codigo_entregador = ?",
                    this.entregador.getCodigo()
                );
                for (Pedido pedido : listaPedidos) {
                    CMD.msg(pedido.toString());
                    for (ItemPedido item : pedido.getAllItems(conn)) {
                        CMD.msg(item.toString());
                    }
                    CMD.msg("Info do Restaurante:");
                    var ent = pedido.getRestaurante(conn);
                    CMD.msg(ent.toString());
                    CMD.msg("Info do Cliente:");
                    var cliente = pedido.getCliente(conn);
                    var end_res = cliente.getEndereco(conn);
                    CMD.msg(cliente.toString());
                    CMD.msg(end_res.toString());
                    CMD.msg("----");
                }
                break;
            case 3:
                new Veiculo().cadastroCMD(conn, this.entregador.getCodigo());
                break;
            case 4:
                var listaVeiculos = this.entregador.getAllVeiculos(conn);

                for (Veiculo veiculo : listaVeiculos) {
                    CMD.msg(veiculo.toString());
                }
            case 5:
                CMD.msg(entregador.toString());
                break;
            case 6:
                this.entregador.cadastroCMD(conn);
                break;
            case 7:
                sair = true;
                break;
            default:
                CMD.msg("Seleção invalida, digite denovo");
                break;
        }

        return sair;
    }

    public void cadastro() throws SQLException, Exception {
        this.entregador.cadastroCMD(conn);
    }

    private void acessarTarefa() throws SQLException, Exception {
        var pedidosDoEntregador = new Pedido().getCollection(
            conn,
            "WHERE status = 'E' AND codigo_entregador = ?",
            this.entregador.getCodigo()
        );
        // Acessar pedido em andamento
        if (pedidosDoEntregador.size() >= 1) {
            var pedidoExec = pedidosDoEntregador.getFirst();
            var statusAtualizado = false;
            var statusNovo = "";

            while (!statusAtualizado) {
                statusNovo = CMD.promptLine(
                    "O Pedido em execução por você é este, deseja mudar para qual status? " +
                        "(E = EM ENTREGA, F = ENTREGUE, C = CANCELADO)",
                    1
                );
                if (
                    !statusNovo.contains("E") &&
                    !statusNovo.contains("F") &&
                    !statusNovo.contains("C")
                ) {
                    CMD.msg("Opção invalida, escreva denovo");
                    continue;
                }
                statusAtualizado = true;
            }

            pedidoExec.setStatus(statusNovo.charAt(0));
            pedidoExec.sendUpdate(conn);

            CMD.msg("Status do pedido atualizado!");
        } else {
            // Procurar pedidos prontos
            var pedidosProntos = new Pedido().getCollection(
                conn,
                "WHERE status = 'P'"
            );
            if (pedidosProntos.size() >= 1) {
                CMD.msg("Estes pedidos estão prontos para serem entregues:");
                for (Pedido pedido : pedidosProntos) {
                    CMD.msg(pedido.toString());
                    var itens = pedido.getAllItems(conn);
                    var rest = pedido.getRestaurante(conn);
                    var rest_end = rest.getEndereco(conn);
                    var cliente = pedido.getCliente(conn);
                    var cliente_end = cliente.getEndereco(conn);
                    CMD.msg("Itens no Pedido");
                    for (ItemPedido item : itens) {
                        CMD.msg(item.toString());
                    }
                    CMD.msg("Informações do Restaurante");
                    CMD.msg(rest.toString());
                    CMD.msg(rest_end.toString());
                    CMD.msg("Informações do Cliente");
                    CMD.msg(cliente.toString());
                    CMD.msg(cliente_end.toString());
                }
                var id = Integer.valueOf(
                    CMD.promptLine(
                        "Escolha qual pedido deseja entregar pelo ID",
                        3
                    )
                );
                var pedidoSelecionado = new Pedido();
                for (Pedido pedido : pedidosProntos) {
                    if (pedido.getCodigo() == id) {
                        pedidoSelecionado = pedido;
                        break;
                    }
                }
                if (!pedidoSelecionado.doesItExist()) {
                    CMD.msg("Pedido não encontrado, saindo...");
                    return;
                }
                pedidoSelecionado.setStatus('E');
                pedidoSelecionado.setCodigoEntregador(
                    this.entregador.getCodigo()
                );
                CMD.msg("Tarefa confirmada, vai la e faz a entrega");
            } else {
                // Caso todos os casos falhar
                CMD.msg("Não há entregas para realizar, fica em paz");
            }
        }
    }
}
