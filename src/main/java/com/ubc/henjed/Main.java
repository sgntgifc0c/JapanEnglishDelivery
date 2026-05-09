package com.ubc.henjed;

import java.sql.SQLException;

import com.ubc.henjed.model.Cliente;

// import org.postgresql.ds.PGSimpleDataSource;

public class Main {
      public static void main(String[] args) throws SQLException, Exception {
        var conn = Database.init();

        var cliente = new Cliente();
        // cliente.setNome("Gilberto Gil");
        // cliente.setCpf("98765432110");
        // cliente.setEmail("gilbertogil@globo.com");

        // cliente.sendCreate(conn);

        /* 
        cliente.getByCodigo(conn, 1);
        cliente.setNome("Jailson Mendes");
        cliente.sendUpdate(conn);

        */

        var col = cliente.getCollection(conn, "");

        for (Cliente cli : col) {
          System.out.println(cli.getNome());
        }

        conn.close();
      }
}//end main