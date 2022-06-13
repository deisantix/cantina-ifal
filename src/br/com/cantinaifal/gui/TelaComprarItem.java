package br.com.cantinaifal.gui;

import java.util.List;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.event.*;
import java.awt.Color;
import java.sql.ResultSet;
import br.com.cantinaifal.estoque.Estoque;

public class TelaComprarItem extends JFrame {

    private Estoque estoque;

    private JScrollPane painelProdutos;
    private String[] colunasTabela = {"CODIGO", "DESCRICAO", "PRECO", "QUANTIDADE"};
    private DefaultTableModel modeloTabela;
    private JTable tabelaProdutos;

    private JOptionPane message;
    private JDialog dialog;

    private JLabel lblCodigo;
    private JLabel lblQuantidade;
    private JTextField txtCodigo;
    private JTextField txtQuantidade;

    private JButton btnComprar;
    private JButton btnFinalizar;
    
    public TelaComprarItem(Estoque estoque) {
        this.estoque = estoque;
        this.message = new JOptionPane();

        setSize(600, 300);
        setTitle("Comprar Produto");
        setVisible(true);
        setResizable(false);
        setLayout(null);
        setLocationRelativeTo(null);

        this.painelProdutos = new JScrollPane();
        this.painelProdutos.setVisible(true);
        this.painelProdutos.setLayout(null);
        this.painelProdutos.setBounds(10, 10, 580, 150);
        // this.painelProdutos.setBackground(Color.BLUE);
        add(this.painelProdutos);

        this.desenharTabelaProdutos();

        this.lblCodigo = new JLabel("Código do produto: ");
        this.lblCodigo.setBounds(10, 170, 200, 20);
        add(this.lblCodigo);

        this.txtCodigo = new JTextField();
        this.txtCodigo.setBounds(10, 190, 200, 30);
        add(this.txtCodigo);

        this.lblQuantidade = new JLabel("Quantidade desejada: ");
        this.lblQuantidade.setBounds(230, 170, 200, 20);
        add(this.lblQuantidade);

        this.txtQuantidade = new JTextField();
        this.txtQuantidade.setBounds(230, 190, 200, 30);
        add(this.txtQuantidade);

        this.btnComprar = new JButton("COMPRAR");
        this.btnComprar.setBounds(150, 230, 150, 30);
        add(this.btnComprar);

        this.btnFinalizar = new JButton("FINALIZAR");
        this.btnFinalizar.setBounds(320, 230, 150, 30);
        this.btnFinalizar.setFocusPainted(false);
        add(this.btnFinalizar);
        
        setBackground(Color.WHITE);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        repaint();

        this.btnComprar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String codigoEmTexto = txtCodigo.getText();
                int itemEscolhido = 0;
                try {
                    itemEscolhido = Integer.parseInt(codigoEmTexto);
                } catch (Exception ex) {
                    message.setMessage("Código inválido!");
                    dialog = message.createDialog(null, "Alerta");
                    dialog.setVisible(true);

                    return;
                }

                try {
                    List<Integer> codigoItens = estoque.retornarListaCodigoProdutos();
                    if(!codigoItens.contains(itemEscolhido)) {
                        throw new Exception("Produto não encontrado");
                    }
                    
                } catch (Exception ex) {
                    message.setMessage(ex.getMessage());
                    dialog = message.createDialog(null, "Alerta");
                    dialog.setVisible(true);

                    return;
                }

                String quantidadeEmTexto = txtQuantidade.getText();
                int quantidadeEscolhida = 0;
                try {
                    quantidadeEscolhida = Integer.parseInt(quantidadeEmTexto);
                } catch (Exception ex) {
                    message.setMessage("Quantidade inválida!");
                    dialog = message.createDialog(null, "Alerta");
                    dialog.setVisible(true);

                    return;
                }

                try {
                    if(
                        quantidadeEscolhida > estoque.retornarQuantidadeProduto(itemEscolhido) ||
                        quantidadeEscolhida <= 0
                    ) {
                        throw new Exception("Quantidade inválida!");
                    }
                } catch (Exception ex) {
                    message.setMessage(ex.getMessage());
                    dialog = message.createDialog(null, "Alerta");
                    dialog.setVisible(true);

                    return;
                }

                try {
                    estoque.comprarItemNoEstoque(itemEscolhido, quantidadeEscolhida);

                    message.setMessage("Compra realizada com sucesso!");
                    dialog = message.createDialog(null, "Sucesso!");
                    dialog.setVisible(true);
                    
                } catch (Exception ex) {
                    message.setMessage("Não foi possível realizar a compra...");
                    dialog = message.createDialog(null, "Alerta");
                    dialog.setVisible(true);

                    return;
                }
                txtCodigo.setText("");
                txtQuantidade.setText("");
                desenharTabelaProdutos();
            }
        });

        this.btnFinalizar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    public void desenharTabelaProdutos() {
        this.painelProdutos.removeAll();

        this.modeloTabela = new DefaultTableModel(this.colunasTabela, 0);
        this.tabelaProdutos = new JTable(this.modeloTabela);
        this.tabelaProdutos.setBounds(0, 20, 580, 130);
        JTableHeader header = tabelaProdutos.getTableHeader();
        header.setBounds(0, 0, 580, 20);

        ResultSet produtos = this.estoque.retornarEstoquePorQuantidade();
        try {
            while(produtos != null & produtos.next()) {
                this.modeloTabela.addRow(new String[] {
                    String.valueOf(produtos.getInt(1)),
                    produtos.getString(2),
                    String.valueOf(produtos.getDouble(3)),
                    String.valueOf(produtos.getInt(4))
                });
            }
        } catch (Exception ex) {
            message.setMessage(ex.getMessage());
            dialog = message.createDialog(null, "Alerta");
            dialog.setVisible(true);
        }
        this.painelProdutos.add(header);
        this.painelProdutos.add(this.tabelaProdutos);
        this.painelProdutos.repaint();
        this.painelProdutos.revalidate();
    }
}
