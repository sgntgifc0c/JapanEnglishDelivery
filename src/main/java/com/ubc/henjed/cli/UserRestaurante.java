package com.ubc.henjed.cli;

import com.ubc.henjed.model.ItemPedido;
import com.ubc.henjed.model.Pedido;
import com.ubc.henjed.model.Produto;
import com.ubc.henjed.model.Restaurante;
import com.ubc.henjed.model.Veiculo;
import com.ubc.henjed.util.CMD;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class UserRestaurante implements IUsuario {

    Restaurante restaurante;
    Scanner scan;
    Connection conn;

    public UserRestaurante(Restaurante cliente, Scanner scan, Connection conn) {
        this.restaurante = cliente;
        this.scan = scan;
        this.conn = conn;
    }

    public void exibirMenu() {
        CMD.msg("1 - Exibir Cardapio");
        CMD.msg("2 - Adicionar um produto no Cardapio");
        CMD.msg("3 - Exibir informações do Restaurante");
        CMD.msg("4 - Editar informações do Restaurante");
        CMD.msg("5 - Editar Produtos no Cardapio");
        CMD.msg("6 - Exibir Histórico de Pedidos");
        CMD.msg("7 - Sair");
    }

    public boolean selecao() throws SQLException, Exception {
        var sair = false;

        switch (scan.nextInt()) {
            case 1:
                exibirCardapio();
                break;
            case 2:
                var prod = new Produto();
                prod.cadastroCMD(conn, this.restaurante.getCodigo());
                break;
            case 3:
                var fmt = this.restaurante.toString();
                var end = this.restaurante.getEndereco(conn);
                var fmt2 = end.toString();
                CMD.msg(fmt + '\n' + fmt2);
                break;
            case 4:
                this.cadastro();
                break;
            case 5:
                var id_prod = CMD.promptLine("Digite o ID do produto", 100);
                var pro = new Produto();
                pro.getByCodigo(conn, Integer.valueOf(id_prod));
                pro.cadastroCMD(conn);
                break;
            case 6:
                var plist = new Pedido().getCollection(
                    conn,
                    "WHERE codigo_restaurante = ?",
                    this.restaurante.getCodigo()
                );

                for (Pedido pedido : plist) {
                    CMD.msg(pedido.toString());
                    for (ItemPedido item : pedido.getAllItems(conn)) {
                        CMD.msg(item.toString());
                    }
                    CMD.msg("Info do Entregador:");
                    var ent = pedido.getEntregador(conn);
                    CMD.msg(ent.toString());
                    for (Veiculo vec : ent.getAllVeiculos(conn)) {
                        CMD.msg(vec.toString());
                    }
                    CMD.msg("Info do Cliente:");
                    var cliente = pedido.getCliente(conn);
                    var end_res = cliente.getEndereco(conn);
                    CMD.msg(cliente.toString());
                    CMD.msg(end_res.toString());
                    CMD.msg("\n");
                }
                break;
            case 7:
                sair = true;
                break;
            default:
                CMD.msg("Opção invalida, digite denovo");
                break;
        }

        return sair;
    }

    public void cadastro() throws SQLException, Exception {
        this.restaurante.cadastroCMD(conn);
    }

    private void exibirCardapio() throws SQLException, Exception {
        var list = new Produto().getCollection(
            conn,
            "WHERE codigo_restaurante = ?",
            this.restaurante.getCodigo()
        );

        for (Produto produto : list) {
            var fmt = String.format(
                "Nome: %s | Descrição: %s | Preço: %f | Categoria: %s | ID: %d",
                produto.getNome(),
                produto.getDescricao(),
                produto.getPreco(),
                produto.getCategoria(),
                produto.getCodigo()
            );
            CMD.msg(fmt);
        }
    }
}
