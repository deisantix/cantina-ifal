package br.com.cantinaifal.gui;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.Color;
import java.awt.event.*;
import java.sql.ResultSet;

import br.com.cantinaifal.Config;
import br.com.cantinaifal.estoque.Estoque;

public class ClienteFrame extends MainFrame {

    private JButton btnOrdenadoPorNome;
    private JButton btnOrdenadoPorQuantidade;
    private JButton btnComprarProduto;

    public ClienteFrame(String setor, JLabel imgSetor, JButton mainButton, Color buttonColor) throws Exception {
        super(setor, imgSetor, mainButton, buttonColor);

        btnOrdenadoPorNome = new JButton("<html><center>" + "VER PRODUTOS ORDENADOS POR NOME" + "</center></html>");
        btnOrdenadoPorNome.setBounds(
            80, 
            this.frameHeight - 100, 
            200, 60
        );
        btnOrdenadoPorNome.setFocusPainted(false);
        btnOrdenadoPorNome.setBackground(new Color(120, 230, 255));
        add(btnOrdenadoPorNome);


        btnOrdenadoPorQuantidade = new JButton("<html><center>" + "VER PRODUTOS ORDENADOS POR QUANTIDADE" + "</center></html>");
        btnOrdenadoPorQuantidade.setBounds(
            290, 
            this.frameHeight - 100, 
            200, 60
        );
        btnOrdenadoPorQuantidade.setBackground(new Color(120, 230, 255));
        add(btnOrdenadoPorQuantidade);


        btnComprarProduto = new JButton("<html><center>" + "COMPRAR PRODUTO" + "</center></html>");
        btnComprarProduto.setBounds(
            500, 
            this.frameHeight - 100, 
            200, 60
        );
        btnComprarProduto.setBackground(new Color(120, 230, 255));
        add(btnComprarProduto);

        repaint();
        this.estoque = new Estoque();

        btnOrdenadoPorNome.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String[] colunas = {
                    "CODIGO", "DESCRIÇÃO", "PREÇO", "QUANTIDADE"
                };
                DefaultTableModel model = new DefaultTableModel(colunas, 0);
                JTable ordenadoPorNome = new JTable(model);
                ordenadoPorNome.setBounds(10, 30, (frameWidth - 40), 200);
                JTableHeader header = ordenadoPorNome.getTableHeader();
                header.setBounds(10, 10, (frameWidth - 40), 20);
                
                ResultSet produtos = estoque.retornarEstoquePorNome();
                try {
                    while(produtos != null & produtos.next()) {
                        model.addRow(new String[] {
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
                painelLateral.add(ordenadoPorNome);
                painelLateral.repaint();
            }
        });
    }

    

    public static void main(String[] args) {
        String setor = "Cliente";

        Icon cliente = new ImageIcon(Config.currentDir + "/assets/imgs/carrinhocompras.png");
        JLabel imgSetor = new JLabel(cliente);

        JButton mainButton = new JButton("LOGIN FUNCIONARIO");
        try {
            ClienteFrame cfm = new ClienteFrame(setor, imgSetor, mainButton, Color.GREEN);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
