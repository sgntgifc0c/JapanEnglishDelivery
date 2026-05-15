package com.ubc.henjed.cli;

import com.ubc.henjed.model.Cliente;
import com.ubc.henjed.model.ItemPedido;
import com.ubc.henjed.model.Pedido;
import com.ubc.henjed.model.Produto;
import com.ubc.henjed.model.Restaurante;
import com.ubc.henjed.model.Veiculo;
import com.ubc.henjed.util.CMD;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class UserCliente implements IUsuario {

    Cliente cliente;
    Scanner scan;
    Connection conn;

    public UserCliente(Cliente cliente, Scanner scan, Connection conn) {
        this.cliente = cliente;
        this.scan = scan;
        this.conn = conn;
    }

    public void exibirMenu() {
        CMD.msg("1 - Listar Restaurantes");
        CMD.msg("2 - Realizar Pedido");
        CMD.msg("3 - Histórico de Pedidos");
        CMD.msg("4 - Exibir Informações do Cliente");
        CMD.msg("5 - Editar Informações do Cliente");
        CMD.msg("6 - Sair");
    }

    public boolean selecao() throws SQLException, Exception {
        var sair = false;

        switch (scan.nextInt()) {
            case 1:
                var rlist = new Restaurante().getCollection(conn);

                for (Restaurante restaurante : rlist) {
                    CMD.msg(restaurante.toString());
                    CMD.msg(restaurante.getEndereco(conn).toString());
                }
                break;
            case 2:
                criarPedido();
                break;
            case 3:
                var plist = new Pedido().getCollection(
                    conn,
                    "WHERE codigo_cliente = ?",
                    this.cliente.getCodigo()
                );

                for (Pedido pedido : plist) {
                    CMD.msg(pedido.toString());
                    CMD.msg("Valor Total: " + pedido.getValorTotal(conn));
                    CMD.msg("Info do Entregador:");
                    var ent = pedido.getEntregador(conn);
                    CMD.msg(ent.toString());
                    for (Veiculo vec : ent.getAllVeiculos(conn)) {
                        CMD.msg(vec.toString());
                    }
                    CMD.msg("Info do Restaurante:");
                    var restaurante = pedido.getRestaurante(conn);
                    var end_res = restaurante.getEndereco(conn);
                    CMD.msg(restaurante.toString());
                    CMD.msg(end_res.toString());
                    CMD.msg("----");
                }
                break;
            case 4:
                CMD.msg(this.cliente.toString());
                var cend = this.cliente.getEndereco(conn);
                CMD.msg(cend.toString());
                break;
            case 5:
                this.cliente.cadastroCMD(conn);
                break;
            case 6:
                sair = true;
                break;
            default:
                CMD.msg("Opção invalida, digite denovo");
                break;
        }

        return sair;
    }

    public void cadastro() throws SQLException, Exception {
        this.cliente.cadastroCMD(conn);
    }

    private void criarPedido() throws SQLException, Exception {
        var pedido = new Pedido();
        pedido.cadastroCMD(conn, this.cliente.getCodigo());

        if (!pedido.doesItExist()) {
            return;
        }

        var pararItems = false;

        var cardapio = pedido.getRestaurante(conn).getCardapio(conn);
        for (Produto produto : cardapio) {
            CMD.msg("Cardapio do restaurante selecionado:");
            CMD.msg(produto.toString());
        }
        while (!pararItems) {
            if (CMD.YorN("Adicionar itens no pedido?")) {
                var ip = new ItemPedido();
                ip.cadastroCMD(conn, pedido.getCodigo());
                if (
                    ip.getProduto(conn).getCodigoRestaurante() !=
                    pedido.getCodigoRestaurante()
                ) {
                    ip.sendDelete(conn);
                    CMD.msg(
                        "ID Invalido, não esta disponivel no cardapio do restaurante, escreva outro ID"
                    );
                }
            } else {
                pararItems = true;
            }
        }

        if (pedido.getAllItems(conn).size() == 0) {
            CMD.msg("Nenhum item foi adicionado, Deletando pedido...");
            pedido.sendDelete(conn);
        }

        CMD.msg("Total sera: " + pedido.getValorTotal(conn));
        if (CMD.YorN("Pagar Pedido?")) {
            CMD.msg("Obrigado, seu pedido chegara em breve!");
        } else {
            pedido.setStatus('C');
            pedido.sendUpdate(conn);
            CMD.msg("Pedido cancelado, voltando");
        }
    }
}
