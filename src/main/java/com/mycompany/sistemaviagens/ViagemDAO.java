package com.mycompany.sistemaviagens;

import java.sql.*;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class ViagemDAO {

    static Scanner sc = new Scanner(System.in);

    // =========================
    // CADASTRAR VIA TELA
    // =========================
    public void cadastrarViaTela(String nome, String email, String telefone,
                                 String cidade, String pais, String data, String status) {

        try (Connection con = Conexao.conectar()) {

            int idCliente;

            PreparedStatement checkCliente = con.prepareStatement(
                    "SELECT id_cliente FROM clientes WHERE email = ?");
            checkCliente.setString(1, email);
            ResultSet rsCliente = checkCliente.executeQuery();

            if (rsCliente.next()) {
                idCliente = rsCliente.getInt("id_cliente");
            } else {
                PreparedStatement ps = con.prepareStatement(
                        "INSERT INTO clientes (nome, email, telefone) VALUES (?, ?, ?)",
                        Statement.RETURN_GENERATED_KEYS);

                ps.setString(1, nome);
                ps.setString(2, email);
                ps.setString(3, telefone);
                ps.executeUpdate();

                ResultSet rs = ps.getGeneratedKeys();
                rs.next();
                idCliente = rs.getInt(1);
            }

            int idDestino;

            PreparedStatement checkDestino = con.prepareStatement(
                    "SELECT id_destino FROM destino WHERE cidade = ? AND pais = ?");
            checkDestino.setString(1, cidade);
            checkDestino.setString(2, pais);
            ResultSet rsDestino = checkDestino.executeQuery();

            if (rsDestino.next()) {
                idDestino = rsDestino.getInt("id_destino");
            } else {
                PreparedStatement ps = con.prepareStatement(
                        "INSERT INTO destino (cidade, pais) VALUES (?, ?)",
                        Statement.RETURN_GENERATED_KEYS);

                ps.setString(1, cidade);
                ps.setString(2, pais);
                ps.executeUpdate();

                ResultSet rs = ps.getGeneratedKeys();
                rs.next();
                idDestino = rs.getInt(1);
            }

            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO reserva (data_viagem, status, fk_id_cliente, fk_id_destino) VALUES (?, ?, ?, ?)");

            ps.setDate(1, Date.valueOf(data));
            ps.setString(2, status);
            ps.setInt(3, idCliente);
            ps.setInt(4, idDestino);
            ps.executeUpdate();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
        }
    }

    // =========================
    // LISTAR
    // =========================
    public static void listar() {
        try (Connection con = Conexao.conectar()) {

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(
                    "SELECT r.id_reserva, c.nome, d.cidade, d.pais, r.data_viagem, r.status " +
                    "FROM reserva r " +
                    "JOIN clientes c ON r.fk_id_cliente = c.id_cliente " +
                    "JOIN destino d ON r.fk_id_destino = d.id_destino");

            while (rs.next()) {
                System.out.println(
                        "\nID: " + rs.getInt("id_reserva") +
                        " | Cliente: " + rs.getString("nome") +
                        " | Destino: " + rs.getString("cidade") + " - " + rs.getString("pais") +
                        " | Data: " + rs.getDate("data_viagem") +
                        " | Status: " + rs.getString("status"));
            }

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
