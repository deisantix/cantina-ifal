package br.com.cantinaifal.gui;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.Color;
import java.awt.event.*;
import java.sql.ResultSet;
import br.com.cantinaifal.Config;
import br.com.cantinaifal.estoque.Estoque;

public class FuncionarioFrame extends MainFrame {

    private Estoque estoque;
    private ClienteFrame frameCliente;

    private String[] colunasTabela = {"CODIGO", "DESCRIÇÃO", "PREÇO", "QUANTIDADE"};
    private DefaultTableModel modeloTabela;
    private JTable tabelaProdutos;

    private JButton btnAdicionarItens;
    private JButton btnBaixarVendidos;
    private JButton btnOrdenadoPorNome;
    private JButton btnOrdenadoPorQuantidade;
    private JButton btnBaixaQuantidade;
    private JButton btnLucro;

    public FuncionarioFrame(
        String setor, 
        JLabel imgSetor, 
        JButton mainButton, 
        Color buttonColor, 
        Estoque estoque
    ) {
        super(setor, imgSetor, mainButton, buttonColor);
        this.estoque = estoque;

        // reconfigurando tamanho do painel de tabela
        this.painelLateral.setBounds(
            (this.frameWidth / 2) - (this.frameWidth - 20) / 2, 
            200, 
            (this.frameWidth - 20), 200
        );

        this.btnAdicionarItens = new JButton("<html><center>" + "ADICIONAR ITENS AO ESTOQUE" + "</center></html>");
        this.btnAdicionarItens.setBounds(
            80,
            this.frameHeight - 180, 
            200, 60
        );
        this.btnAdicionarItens.setFocusPainted(false);
        this.btnAdicionarItens.setBackground(new Color(120, 230, 255));
        add(this.btnAdicionarItens);


        this.btnBaixarVendidos = new JButton("<html><center>" + "DAR BAIXA NOS ITENS VENDIDOS" + "</center></html>");
        this.btnBaixarVendidos.setBounds(
            300,
            this.frameHeight - 180, 
            200, 60
        );
        this.btnBaixarVendidos.setFocusPainted(false);
        this.btnBaixarVendidos.setBackground(new Color(120, 230, 255));
        add(this.btnBaixarVendidos);


        this.btnOrdenadoPorNome = new JButton("<html><center>" + "VER PRODUTOS ORDENADOS POR NOME" + "</center></html>");
        this.btnOrdenadoPorNome.setBounds(
            520,
            this.frameHeight - 180, 
            200, 60
        );
        this.btnOrdenadoPorNome.setFocusPainted(false);
        this.btnOrdenadoPorNome.setBackground(new Color(120, 230, 255));
        add(this.btnOrdenadoPorNome);


        this.btnOrdenadoPorQuantidade = new JButton("<html><center>" + "VER PRODUTOS ORDENADOS POR QUANTIDADE" + "</center></html>");
        this.btnOrdenadoPorQuantidade.setBounds(
            80,
            this.frameHeight - 110, 
            200, 60
        );
        this.btnOrdenadoPorQuantidade.setFocusPainted(false);
        this.btnOrdenadoPorQuantidade.setBackground(new Color(120, 230, 255));
        add(this.btnOrdenadoPorQuantidade);


        this.btnBaixaQuantidade = new JButton("<html><center>" + "VISUALIZAR PRODUTOS EM BAIXA QUANTIDADE" + "</center></html>");
        this.btnBaixaQuantidade.setBounds(
            300,
            this.frameHeight - 110, 
            200, 60
        );
        this.btnBaixaQuantidade.setFocusPainted(false);
        this.btnBaixaQuantidade.setBackground(new Color(120, 230, 255));
        add(this.btnBaixaQuantidade);


        this.btnLucro = new JButton("<html><center>" + "VISUALIZAR RESUMO DE LUCRO" + "</center></html>");
        this.btnLucro.setBounds(
            520,
            this.frameHeight - 110, 
            200, 60
        );
        this.btnLucro.setFocusPainted(false);
        this.btnLucro.setBackground(new Color(120, 230, 255));
        add(this.btnLucro);

        repaint();

        this.btnAdicionarItens.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TelaAdicionarItem addI = new TelaAdicionarItem(estoque);
            }
        });

        this.btnOrdenadoPorNome.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                painelLateral.removeAll();

                modeloTabela = new DefaultTableModel(colunasTabela, 0);
                tabelaProdutos = new JTable(modeloTabela);
                tabelaProdutos.setBounds(10, 30, (frameWidth - 40), 160);
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
                tabelaProdutos.setBounds(10, 30, (frameWidth - 40), 160);
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

        this.btnBaixaQuantidade.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                painelLateral.removeAll();

                modeloTabela = new DefaultTableModel(colunasTabela, 0);
                tabelaProdutos = new JTable(modeloTabela);
                tabelaProdutos.setBounds(10, 30, (frameWidth - 40), 160);
                JTableHeader header = tabelaProdutos.getTableHeader();
                header.setBounds(10, 10, (frameWidth - 40), 20);
                
                ResultSet produtos = estoque.retornarEstoqueEmBaixaQuantidade();
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
    }

    public static void main(String[] args) {
        String setor = "Funcionário";

        Icon adm = new ImageIcon(Config.currentDir + "/assets/imgs/adm.png");
        JLabel imgSetor = new JLabel(adm);

        JButton mainButton = new JButton("DESCONECTAR");
        Color buttonColor = new Color(0, 90, 190);

        try {
            FuncionarioFrame funcionarioFrame = new FuncionarioFrame(setor, imgSetor, mainButton, buttonColor, new Estoque());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

}
