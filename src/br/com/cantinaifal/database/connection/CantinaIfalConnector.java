package br.com.cantinaifal.database.connection;


import java.sql.Connection;
import java.sql.SQLException;

import br.com.cantinaifal.database.importer.CantinaIfalDBInstaller;


public class CantinaIfalConnector {

    public static Connection connect() throws SQLException {
        Connection con = null;
        try {
            con = ConnectionFactory.getConnection("cantinaifal");
            System.out.println("Banco conectado com sucesso!");
            
            return con;

        } catch (SQLException e1) {

            // bd de auxilio para poder criar o bd correto
            con = ConnectionFactory.getConnection("mysql");

            try {
                CantinaIfalDBInstaller.installDB(con);
                System.out.println("Banco criado com sucesso!");

            } catch (Exception e2) {
                System.out.println(e2.getMessage());
            }

        }

        con = ConnectionFactory.getConnection("cantinaifal");
        System.out.println("Banco conectado com sucesso!");

        return con;
    }
}
