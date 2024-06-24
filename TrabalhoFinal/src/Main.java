import GUI.LoginGUI;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame ti = new JFrame("Tela Inicial");
        ti.setContentPane(new LoginGUI().loginPanel);
        ti.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ti.pack();
        ti.setResizable(true);
        ti.setSize(830, 870);
        ti.setLocationRelativeTo(null);
        ti.setVisible(true);
    }
}