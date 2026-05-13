package com.ubc.henjed.cli;

import com.ubc.henjed.model.Cliente;
import java.util.Scanner;

public class UserCliente implements IUsuario {
    Cliente cliente;
    Scanner scan;

    public UserCliente(Cliente cliente, Scanner scan) {
        this.cliente = cliente;
        this.scan = scan;
    }

    public void exibirMenu() {

    }

    public boolean selecao() {
        return true;
    }

    public void cadastro() {
        
    }
}
