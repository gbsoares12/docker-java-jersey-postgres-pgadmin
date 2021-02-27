package br.udesc.dao;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Andrew, Viviane
 */
public class Conexao {

    private static Connection connection = null;

    public static Connection getConnection() {
        if (connection != null) {
            return connection;
        } else {
            try {

                String url = "jdbc:postgresql://127.0.0.1/pobreflixbd";
                String usuario = "postgres";
                String senha = "postgres";

                Class.forName("org.postgresql.Driver");

                connection = (Connection)DriverManager.getConnection(url, usuario, senha);

            } catch (Exception e) {
                
                 e.printStackTrace();
            }
            return connection;
        }

    }
}
