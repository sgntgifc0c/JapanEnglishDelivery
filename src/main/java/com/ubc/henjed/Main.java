package com.ubc.henjed;

import java.sql.SQLException;
import java.util.Scanner;

import com.ubc.henjed.util.Log;
import com.ubc.henjed.cli.IUsuario;
import com.ubc.henjed.cli.UserCliente;
import com.ubc.henjed.model.*;

// import org.postgresql.ds.PGSimpleDataSource;

public class Main {
      public static void main(String[] args) throws SQLException, Exception {
        var conn = Database.init();
        var fecharPrograma = false;
        var scan = new Scanner(System.in);

        Log.msg("JAPAN ENGLISH DELIVERY");

        while (!fecharPrograma) {
          Model<?> model = null;
          IUsuario ui = null;
          boolean close = false;

          Log.msg("Logar como:");
          Log.msg("1 - Cliente");
          Log.msg("2 - Restaurante");
          Log.msg("3 - Entregador");
          Log.msg("4 - Sair");

          switch (scan.nextInt()) {
            case 1:
              model = new Cliente();
              ui = new UserCliente((Cliente) model, scan);
              break;
            case 4:
              fecharPrograma = true;
              Log.msg("Tchau");
              break;
            default:
              Log.msg("Invalido, digite um dos numeros.");
              break;
          }

          if (model == null || ui == null) {
            continue;
          }

          Log.msg("Login ou Cadastro?");
          Log.msg("1 - Login");
          Log.msg("2 - Cadastro");
          Log.msg("3 - Voltar");

          switch (scan.nextInt()) {
            case 1:
              Log.msg("Digite o ID do " + model.getTablename());
              int id = scan.nextInt();
              model.getByCodigo(conn, id);
              break;
            case 2:
              ui.cadastro();
              break;
            case 3:
              break;
            default:
              Log.msg("Opção invalida, voltando...");
              break;
          }

          if (!model.doesItExist()) {
            Log.msg("Falha na procura do "+model.getTablename()+", Voltando...");
            continue;
          }

          while (!close) {
            Log.msg("Logado como "+model.getTablename()+", ID: "+model.getCodigo());
            ui.exibirMenu();
            ui.selecao();
          }
        }

        scan.close();
        conn.close();
      }
}//end main