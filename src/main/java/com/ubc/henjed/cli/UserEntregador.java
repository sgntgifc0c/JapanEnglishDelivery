package com.ubc.henjed.cli;

import com.ubc.henjed.model.Entregador;
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

    public void exibirMenu() {}

    public boolean selecao() throws SQLException, Exception {
        return true;
    }

    public void cadastro() throws SQLException, Exception {
        this.entregador.cadastroCMD(conn);
    }
}
