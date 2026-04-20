package com.mycompany.sistemaviagens;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.sql.*;

public class Tela extends JFrame {

    CardLayout layout = new CardLayout();
    JPanel painel = new JPanel(layout);

    public Tela() {
        setTitle("Sistema de Viagens");
        setSize(700, 550);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        painel.add(menu(), "menu");
        painel.add(cadastro(), "cadastro");
        painel.add(lista(), "lista");
        painel.add(atualizar(), "atualizar");
        painel.add(excluir(), "excluir");

        add(painel);
        layout.show(painel, "menu");

        setVisible(true);
    }

    // ================= MENU
    private JPanel menu() {
        JPanel p = new JPanel(new GridLayout(6, 1, 10, 10));

        JButton cad = new JButton("Cadastrar");
        JButton list = new JButton("Listar");
        JButton att = new JButton("Atualizar");
        JButton exc = new JButton("Excluir");
        JButton sair = new JButton("Sair");

        cad.addActionListener(e -> layout.show(painel, "cadastro"));
        list.addActionListener(e -> layout.show(painel, "lista"));
        att.addActionListener(e -> layout.show(painel, "atualizar"));
        exc.addActionListener(e -> layout.show(painel, "excluir"));
        sair.addActionListener(e -> System.exit(0));

        p.add(new JLabel("MENU PRINCIPAL", SwingConstants.CENTER));
        p.add(cad);
        p.add(list);
        p.add(att);
        p.add(exc);
        p.add(sair);

        return p;
    }

    // ================= CADASTRO
    private JPanel cadastro() {
        JPanel p = new JPanel(new GridLayout(9, 2, 5, 5));

        JTextField nome = new JTextField();
        JTextField email = new JTextField();
        JTextField tel = new JTextField();
        JTextField cidade = new JTextField();
        JTextField pais = new JTextField();
        JTextField data = new JTextField();
        JTextField status = new JTextField();

        JButton salvar = new JButton("Salvar");
        JButton voltar = new JButton("Voltar");

        p.add(new JLabel("Nome:")); p.add(nome);
        p.add(new JLabel("Email:")); p.add(email);
        p.add(new JLabel("Telefone:")); p.add(tel);
        p.add(new JLabel("Cidade:")); p.add(cidade);
        p.add(new JLabel("País:")); p.add(pais);
        p.add(new JLabel("Data (YYYY-MM-DD):")); p.add(data);
        p.add(new JLabel("Status:")); p.add(status);

        p.add(salvar);
        p.add(voltar);

        salvar.addActionListener(e -> {
            new ViagemDAO().cadastrarViaTela(
                    nome.getText(), email.getText(), tel.getText(),
                    cidade.getText(), pais.getText(),
                    data.getText(), status.getText()
            );
            JOptionPane.showMessageDialog(null, "✔ Cadastrado!");
            layout.show(painel, "menu");
        });

        voltar.addActionListener(e -> layout.show(painel, "menu"));

        return p;
    }

    // ================= LISTA (AGORA COM AREA PRÓPRIA)
    private JPanel lista() {
        JPanel p = new JPanel(new BorderLayout());

        JTextArea areaLista = new JTextArea();
        areaLista.setEditable(false);

        JButton voltar = new JButton("Voltar");

        p.add(new JScrollPane(areaLista), BorderLayout.CENTER);
        p.add(voltar, BorderLayout.SOUTH);

        voltar.addActionListener(e -> layout.show(painel, "menu"));

        p.addComponentListener(new ComponentAdapter() {
            public void componentShown(ComponentEvent evt) {
                carregar(areaLista);
            }
        });

        return p;
    }

    // ================= ATUALIZAR
    private JPanel atualizar() {
        JPanel p = new JPanel(new BorderLayout());

        JTextArea areaAtt = new JTextArea();
        areaAtt.setEditable(false);

        JPanel form = new JPanel(new GridLayout(4, 2));

        JTextField id = new JTextField();
        JTextField data = new JTextField();
        JTextField status = new JTextField();

        JButton atualizar = new JButton("Atualizar");
        JButton voltar = new JButton("Voltar");

        form.add(new JLabel("ID:")); form.add(id);
        form.add(new JLabel("Data:")); form.add(data);
        form.add(new JLabel("Status:")); form.add(status);
        form.add(atualizar); form.add(voltar);

        p.add(form, BorderLayout.NORTH);
        p.add(new JScrollPane(areaAtt), BorderLayout.CENTER);

        atualizar.addActionListener(e -> {
            try (Connection con = Conexao.conectar()) {
                PreparedStatement ps = con.prepareStatement(
                        "UPDATE reserva SET data_viagem=?, status=? WHERE id_reserva=?"
                );
                ps.setDate(1, Date.valueOf(data.getText()));
                ps.setString(2, status.getText());
                ps.setInt(3, Integer.parseInt(id.getText()));
                ps.executeUpdate();

                JOptionPane.showMessageDialog(null, "✔ Atualizado!");
                carregar(areaAtt);

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erro: " + ex.getMessage());
            }
        });

        voltar.addActionListener(e -> layout.show(painel, "menu"));

        p.addComponentListener(new ComponentAdapter() {
            public void componentShown(ComponentEvent evt) {
                carregar(areaAtt);
            }
        });

        return p;
    }

    // ================= EXCLUIR
    private JPanel excluir() {
        JPanel p = new JPanel(new BorderLayout());

        JTextArea areaExc = new JTextArea();
        areaExc.setEditable(false);

        JPanel form = new JPanel(new GridLayout(2, 2));

        JTextField id = new JTextField();

        JButton excluir = new JButton("Excluir");
        JButton voltar = new JButton("Voltar");

        form.add(new JLabel("ID:")); form.add(id);
        form.add(excluir); form.add(voltar);

        p.add(form, BorderLayout.NORTH);
        p.add(new JScrollPane(areaExc), BorderLayout.CENTER);

        excluir.addActionListener(e -> {
            try (Connection con = Conexao.conectar()) {
                PreparedStatement ps = con.prepareStatement(
                        "DELETE FROM reserva WHERE id_reserva=?"
                );
                ps.setInt(1, Integer.parseInt(id.getText()));
                ps.executeUpdate();

                JOptionPane.showMessageDialog(null, "✔ Excluído!");
                carregar(areaExc);

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erro: " + ex.getMessage());
            }
        });

        voltar.addActionListener(e -> layout.show(painel, "menu"));

        p.addComponentListener(new ComponentAdapter() {
            public void componentShown(ComponentEvent evt) {
                carregar(areaExc);
            }
        });

        return p;
    }

    // ================= MÉTODO GENÉRICO
    private void carregar(JTextArea area) {
        try (Connection con = Conexao.conectar()) {

            String sql =
                    "SELECT r.id_reserva, c.nome, d.cidade, d.pais, r.data_viagem, r.status " +
                    "FROM reserva r " +
                    "JOIN clientes c ON r.fk_id_cliente = c.id_cliente " +
                    "JOIN destino d ON r.fk_id_destino = d.id_destino";

            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            StringBuilder sb = new StringBuilder();

            while (rs.next()) {
                sb.append("ID: ").append(rs.getInt("id_reserva"))
                  .append(" | ").append(rs.getString("nome"))
                  .append(" | ").append(rs.getString("cidade"))
                  .append(" - ").append(rs.getString("pais"))
                  .append(" | ").append(rs.getDate("data_viagem"))
                  .append(" | ").append(rs.getString("status"))
                  .append("\n");
            }

            if (sb.length() == 0) {
                sb.append("Nenhuma reserva encontrada.");
            }

            area.setText(sb.toString());

        } catch (Exception e) {
            area.setText("Erro: " + e.getMessage());
        }
    }
}
