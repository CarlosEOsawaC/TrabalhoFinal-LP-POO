package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ProdutosGUI {

    public JPanel ProdutosPanel;
    private JButton btAddCarrinho;
    private JButton btCarrinho;

    private JLabel imgCarrinho;
    private JLabel imgFeijao;
    private JLabel imgOleo;
    private JLabel imgMacarrao;
    private JLabel imgSuco;
    private JLabel imgCocaCola;

    private JSpinner spiOleo;
    private JSpinner spiFeijao;
    private JSpinner spiArroz;
    private JSpinner spiMacarrao;
    private JSpinner spiSuco;
    private JSpinner spiCocaCola;

    private JButton btSair;

    private JPanel ArrozProd;
    private JPanel FeijaoProd;
    private JPanel OleoProd;
    private JPanel CocaColaProd;
    private JPanel SucoProd;
    private JPanel MacarraoProd;
    private JLabel imgArroz;

    private JCheckBox CBArroz;
    private JCheckBox CBFeijao;
    private JCheckBox CBOleo;
    private JCheckBox CBMacarrao;
    private JCheckBox CBSuco;
    private JCheckBox CBCocaCola;
    private JLabel valorArroz;
    private JLabel valorFeijão;
    private JLabel valorOleo;
    private JLabel valorMacarrao;
    private JLabel valorCocaCola;
    private JLabel valorSuco;

    private final ArrayList<Itens> itensCarrinho = new ArrayList<>();

    public ProdutosGUI() {
        btAddCarrinho.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvarnoCarrinho();
            }
        });
        btCarrinho.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iraoCarrinho();
            }
        });
        btSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProdutosPanel.getTopLevelAncestor().setVisible(false);
            }
        });
    }

    private void salvarnoCarrinho() {
        if (CBArroz.isSelected()) {
            itensCarrinho.add(new Itens("Arroz", 35, (Integer) spiArroz.getValue()));
        }
        if (CBFeijao.isSelected()) {
            itensCarrinho.add(new Itens("Feijão", 6, (Integer) spiFeijao.getValue()));
        }
        if (CBOleo.isSelected()) {
            itensCarrinho.add(new Itens("Oleo", 5, (Integer) spiOleo.getValue()));
        }
        if (CBMacarrao.isSelected()) {
            itensCarrinho.add(new Itens("Macarrão", 2, (Integer) spiMacarrao.getValue()));
        }
        if (CBSuco.isSelected()) {
            itensCarrinho.add(new Itens("Suco de Laranja", 12, (Integer) spiSuco.getValue()));
        }
        if (CBCocaCola.isSelected()) {
            itensCarrinho.add(new Itens("Coca Cola", 9, (Integer) spiCocaCola.getValue()));
        }
        resumoCarrinho();
    }

    private void resumoCarrinho() {
        StringBuilder resumo = new StringBuilder();
        double valorTotal = 0;

        for (Itens item : itensCarrinho) {
            resumo.append(item.getQuantidade())
                    .append("x ")
                    .append(item.getNome())
                    .append(": R$")
                    .append(item.getValor() * item.getQuantidade())
                    .append("\n");
            valorTotal += item.getValor() * item.getQuantidade();
        }
        resumo.append("Valor total: R$").append(valorTotal);

        int confirm = JOptionPane.showConfirmDialog(null, resumo.toString(), "Confirmar Carrinho", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            iraoCarrinho();
        }
    }

    private void iraoCarrinho() {
        CarrinhoGUI Carrinho = new CarrinhoGUI(itensCarrinho);
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(ProdutosPanel);
        frame.setContentPane(Carrinho.CarrinhoPainel);
        frame.setLocationRelativeTo(null);
        frame.setSize(800,800);
        frame.revalidate();
        frame.repaint();
    }

    public static void main(String[] args) {
        JFrame pro = new JFrame("Produtos");
        pro.setContentPane(new ProdutosGUI().ProdutosPanel);
        pro.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pro.pack();
        pro.setVisible(true);
        pro.setSize(500, 500);
    }
}
