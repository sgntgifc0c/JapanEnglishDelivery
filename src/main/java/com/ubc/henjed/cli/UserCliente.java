package com.ubc.henjed.cli;

import com.ubc.henjed.model.Cliente;
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

    public void exibirMenu() {}

    public boolean selecao() throws SQLException, Exception {
        return true;
    }

    public void cadastro() throws SQLException {
        this.cliente.cadastroCMD(conn);
    }
}
