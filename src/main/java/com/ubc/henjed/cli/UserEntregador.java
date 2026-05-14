package com.ubc.henjed.cli;

import com.ubc.henjed.model.Entregador;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class UserEntregador implements IUsuario {

    Entregador entregador;
    Scanner scan;

    public UserEntregador(Entregador cliente, Scanner scan) {
        this.entregador = cliente;
        this.scan = scan;
    }

    public void exibirMenu() {}

    public boolean selecao() {
        return true;
    }

    public void cadastro(Connection conn) throws SQLException {
        this.entregador.cadastroCMD(conn);
    }
}
