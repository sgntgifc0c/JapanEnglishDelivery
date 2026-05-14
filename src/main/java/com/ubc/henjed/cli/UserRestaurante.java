package com.ubc.henjed.cli;

import com.ubc.henjed.model.Produto;
import com.ubc.henjed.model.Restaurante;
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
        CMD.msg("6 - Sair");
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
                CMD.msg(fmt +'\n'+ fmt2);
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
                sair = true;
                break;
        }

        return sair;
    }

    public void cadastro() throws SQLException {
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
