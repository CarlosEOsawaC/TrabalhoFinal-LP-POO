package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CadastroGUI {
    private JTextField tfNomeUsuario;
    private JTextField tfSenha;
    private JButton btConcluir;
    private JTextField tfEmail;
    private JTextField tfTelefone;
    public JPanel CadastroPanel;

    public CadastroGUI() {
        btConcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = tfNomeUsuario.getText();
                String email = tfEmail.getText();
                String telefone = tfTelefone.getText();
                String senha = tfSenha.getText();

                if (usuario.isEmpty() || email.isEmpty() || telefone.isEmpty() || senha.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Nome de Usuário, e-mail, telefone e senha não podem estar vazios.");
                    return;
                }
                try {
                    Class<?> aClass = Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_senhas", "root", "root");

                    String sql = "INSERT INTO dados_senhas (usuario, email, telefone, senha) VALUES (?, ?, ?, ?)";

                    PreparedStatement statement = connection.prepareStatement(sql);
                    statement.setString(1, usuario);
                    statement.setString(2, email);
                    statement.setString(3, telefone);
                    statement.setString(4, senha);

                    int addedRows = statement.executeUpdate();
                    if (addedRows > 0){
                        JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!");
                        JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(CadastroPanel);
                        topFrame.dispose();
                    }
                    statement.close();
                    connection.close();
                }catch (SQLException throwables) {
                    throwables.printStackTrace();
                }catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame cp = new JFrame("Cadastro");
        cp.setContentPane(new CadastroGUI().CadastroPanel);
        cp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cp.pack();
        cp.setVisible(true);
        cp.setSize(400, 300);
        cp.setLocationRelativeTo(null);
    }
}

