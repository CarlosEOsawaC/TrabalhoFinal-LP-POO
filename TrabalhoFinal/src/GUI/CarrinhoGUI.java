package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CarrinhoGUI {
    public JPanel CarrinhoPainel;
    private JLabel imgLogo;

    private JRadioButton RbtnPix;
    private JRadioButton RbtnCredito;
    private JRadioButton RbtnDebito;

    private JButton btItensCarrinho;
    private JButton btPagar;
    private JButton btVoltar;

    private ButtonGroup grupoPagamento;

    private JFrame telaVendasFrame;

    public CarrinhoGUI(ArrayList<Itens> itensCarrinho){
        grupoPagamento = new ButtonGroup();
        grupoPagamento.add(RbtnPix);
        grupoPagamento.add(RbtnCredito);
        grupoPagamento.add(RbtnDebito);

        btItensCarrinho.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (grupoPagamento.getSelection() != null) {
                    double total = 0;
                    StringBuilder sb = new StringBuilder();
                    for (Itens item : itensCarrinho) {
                        sb.append(item.getNome()).append(": R$ ").append(item.getValor()).append(" x ").append(item.getQuantidade()).append("\n");
                        total += item.getValor() * item.getQuantidade();
                    }
                    sb.append("Total: R$ ").append(total);
                    JOptionPane.showMessageDialog(CarrinhoPainel, sb.toString(), "Itens do Carrinho", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(CarrinhoPainel, "Por favor, selecione uma forma de pagamento.", "Aviso", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        btPagar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JOptionPane.showMessageDialog(null, "Compra finalizada! Obrigado por comprar conosco!");

                ProdutosGUI Produtos = new ProdutosGUI();
                JFrame pro = new JFrame("Tela de Vendas");
                pro.setContentPane(Produtos.ProdutosPanel);
                pro.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                pro.pack();
                pro.setResizable(true);
                pro.setSize(500, 500);
                pro.setLocationRelativeTo(null);
                pro.setVisible(true);
                CarrinhoPainel.getTopLevelAncestor().setVisible(false);
            }
        });

        btVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ProdutosGUI telaVendas = new ProdutosGUI();
                JFrame pro = new JFrame("Tela de Vendas");
                pro.setContentPane(telaVendas.ProdutosPanel);
                pro.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                pro.pack();
                pro.setResizable(true);
                pro.setSize(500, 500);
                pro.setLocationRelativeTo(null);
                pro.setVisible(true);
                CarrinhoPainel.getTopLevelAncestor().setVisible(false);
            }
        });
    }
}
