package com.ubc.henjed.cli;

import com.ubc.henjed.model.Restaurante;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class UserRestaurante implements IUsuario {

    Restaurante restaurante;
    Scanner scan;

    public UserRestaurante(Restaurante cliente, Scanner scan) {
        this.restaurante = cliente;
        this.scan = scan;
    }

    public void exibirMenu() {}

    public boolean selecao() {
        return true;
    }

    public void cadastro(Connection conn) throws SQLException {
        this.restaurante.cadastroCMD(conn);
    }
}
