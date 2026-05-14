package com.ubc.henjed;

import com.ubc.henjed.cli.IUsuario;
import com.ubc.henjed.cli.UserCliente;
import com.ubc.henjed.cli.UserEntregador;
import com.ubc.henjed.cli.UserRestaurante;
import com.ubc.henjed.model.*;
import com.ubc.henjed.util.CMD;
import java.sql.SQLException;
import java.util.Scanner;

// import org.postgresql.ds.PGSimpleDataSource;

public class Main {

    public static void main(String[] args) throws SQLException, Exception {
        var conn = Database.init();
        var fecharPrograma = false;
        var scan = new Scanner(System.in);

        CMD.setScan(scan);

        CMD.msg("JAPAN ENGLISH DELIVERY");

        while (!fecharPrograma) {
            Model<?> model = null;
            IUsuario ui = null;
            boolean close = false;

            CMD.msg("Logar como:");
            CMD.msg("1 - Cliente");
            CMD.msg("2 - Restaurante");
            CMD.msg("3 - Entregador");
            CMD.msg("4 - Sair");

            switch (scan.nextInt()) {
                case 1:
                    model = new Cliente();
                    ui = new UserCliente((Cliente) model, scan);
                    break;
                case 2:
                    model = new Restaurante();
                    ui = new UserRestaurante((Restaurante) model, scan);
                    break;
                case 3:
                    model = new Entregador();
                    ui = new UserEntregador((Entregador) model, scan);
                    break;
                case 4:
                    fecharPrograma = true;
                    CMD.msg("Tchau");
                    break;
                default:
                    CMD.msg("Invalido, digite um dos numeros.");
                    break;
            }

            if (model == null || ui == null) {
                continue;
            }

            CMD.msg("Login ou Cadastro?");
            CMD.msg("1 - Login");
            CMD.msg("2 - Cadastro");
            CMD.msg("3 - Voltar");

            switch (scan.nextInt()) {
                case 1:
                    CMD.msg("Digite o ID do " + model.getTablename());
                    int id = scan.nextInt();
                    model.getByCodigo(conn, id);
                    break;
                case 2:
                    ui.cadastro(conn);
                    break;
                case 3:
                    break;
                default:
                    CMD.msg("Opção invalida, voltando...");
                    break;
            }

            if (!model.doesItExist()) {
                CMD.msg(
                    "Falha na procura do " +
                        model.getTablename() +
                        ", Voltando..."
                );
                continue;
            }

            while (!close) {
                CMD.msg(
                    "Logado como " +
                        model.getTablename() +
                        ", ID: " +
                        model.getCodigo()
                );
                ui.exibirMenu();
                close = ui.selecao();
            }
        }

        scan.close();
        conn.close();
    }
} //end main
