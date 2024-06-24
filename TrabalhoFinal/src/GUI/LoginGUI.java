package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class LoginGUI {
    private JTextField tfUsuario;
    private JPasswordField pfSenha;
    private JButton btEntrar;
    private JButton btSair;
    private JButton btCadastro;
    public JPanel loginPanel;
    private JLabel imgLogo;


    public LoginGUI() {
        btEntrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = tfUsuario.getText();
                char[] senha = pfSenha.getPassword();

                if (usuario.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Usuário ou senha não pode estar vazio.");
                    return;
                }
                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_senhas", "root", "root");
                    PreparedStatement pst = connection.prepareStatement("SELECT * FROM dados_senhas WHERE usuario=? AND senha=?");
                    pst.setString(1, usuario);
                    pst.setString(2, new String(senha));
                    ResultSet rs = pst.executeQuery();

                    if (rs.next()) {
                        ProdutosGUI produtosGUI = new ProdutosGUI();
                        JFrame pGUI = new JFrame("Produtos");
                        pGUI.setContentPane(produtosGUI.ProdutosPanel); // Supondo que 'painelDeVendas' seja o JPanel na TelaVendas
                        pGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        pGUI.pack();
                        pGUI.setVisible(true);
                        pGUI.setLocationRelativeTo(null);
                        pGUI.setSize(500,500);
                    } else {
                        JOptionPane.showMessageDialog(null, "Usuário ou senha incorretos.");
                    }

                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

        btSair.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        btCadastro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CadastroGUI cadastro = new CadastroGUI();
                JFrame cp = new JFrame("Cadastro");
                cp.setContentPane(cadastro.CadastroPanel);
                cp.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                cp.pack();
                cp.setVisible(true);
                cp.setSize(400, 300);
                cp.setLocationRelativeTo(null);
            }
        });
    }
}
