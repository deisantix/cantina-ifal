package br.com.cantinaifal.gui;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.Color;
import java.awt.event.*;
import java.sql.ResultSet;
import br.com.cantinaifal.estoque.Estoque;

public class ClienteFrame extends MainFrame {

    private String[] colunasTabela = {"CODIGO", "DESCRIÇÃO", "PREÇO", "QUANTIDADE"};
    private DefaultTableModel modeloTabela;
    private JTable tabelaProdutos;

    private JButton btnOrdenadoPorNome;
    private JButton btnOrdenadoPorQuantidade;
    private JButton btnComprarProduto;

    public ClienteFrame(String setor, JLabel imgSetor, JButton mainButton, Color buttonColor) throws Exception {
        super(setor, imgSetor, mainButton, buttonColor);

        this.btnOrdenadoPorNome = new JButton("<html><center>" + "VER PRODUTOS ORDENADOS POR NOME" + "</center></html>");
        this.btnOrdenadoPorNome.setBounds(
            80, 
            this.frameHeight - 100, 
            200, 60
        );
        this.btnOrdenadoPorNome.setFocusPainted(false);
        this.btnOrdenadoPorNome.setBackground(new Color(120, 230, 255));
        add(this.btnOrdenadoPorNome);


        this.btnOrdenadoPorQuantidade = new JButton("<html><center>" + "VER PRODUTOS ORDENADOS POR QUANTIDADE" + "</center></html>");
        this.btnOrdenadoPorQuantidade.setBounds(
            290, 
            this.frameHeight - 100, 
            200, 60
        );
        this.btnOrdenadoPorQuantidade.setFocusPainted(false);
        this.btnOrdenadoPorQuantidade.setBackground(new Color(120, 230, 255));
        add(this.btnOrdenadoPorQuantidade);


        this.btnComprarProduto = new JButton("<html><center>" + "COMPRAR PRODUTO" + "</center></html>");
        this.btnComprarProduto.setBounds(
            500, 
            this.frameHeight - 100, 
            200, 60
        );
        this.btnComprarProduto.setFocusPainted(false);
        this.btnComprarProduto.setBackground(new Color(120, 230, 255));
        add(this.btnComprarProduto);

        repaint();
        this.estoque = new Estoque();

        ClienteFrame cfm = this;
        this.mainButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TelaLogin tl = new TelaLogin(estoque, cfm);
            }
        });

        this.btnOrdenadoPorNome.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                painelLateral.removeAll();

                modeloTabela = new DefaultTableModel(colunasTabela, 0);
                tabelaProdutos = new JTable(modeloTabela);
                tabelaProdutos.setBounds(10, 30, (frameWidth - 40), 200);
                JTableHeader header = tabelaProdutos.getTableHeader();
                header.setBounds(10, 10, (frameWidth - 40), 20);
                
                ResultSet produtos = estoque.retornarEstoquePorNome();
                try {
                    while(produtos != null & produtos.next()) {
                        modeloTabela.addRow(new String[] {
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
                
                painelLateral.add(header);
                painelLateral.add(tabelaProdutos);
                painelLateral.repaint();
                painelLateral.revalidate();
            }
        });

        this.btnOrdenadoPorQuantidade.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                painelLateral.removeAll();

                modeloTabela = new DefaultTableModel(colunasTabela, 0);
                tabelaProdutos = new JTable(modeloTabela);
                tabelaProdutos.setBounds(10, 30, (frameWidth - 40), 200);
                JTableHeader header = tabelaProdutos.getTableHeader();
                header.setBounds(10, 10, (frameWidth - 40), 20);
                
                ResultSet produtos = estoque.retornarEstoquePorQuantidade();
                try {
                    while(produtos != null & produtos.next()) {
                        modeloTabela.addRow(new String[] {
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
                
                painelLateral.add(header);
                painelLateral.add(tabelaProdutos);
                painelLateral.repaint();
                painelLateral.revalidate();
            }
        });

        this.btnComprarProduto.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TelaComprarItem comprar = new TelaComprarItem(estoque);
            }
        });
    }

}
