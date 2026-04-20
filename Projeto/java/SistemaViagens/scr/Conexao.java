package com.mycompany.sistemaviagens;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {

    public static Connection conectar() {
        try {
            String url = "jdbc:postgresql://localhost:5432/sistema_viagens";
            String user = "postgres";
            String password = "0207";

            return DriverManager.getConnection(url, user, password);

        } catch (Exception e) {
            System.out.println("Erro na conexão: " + e.getMessage());
            return null;
        }
    }
}
