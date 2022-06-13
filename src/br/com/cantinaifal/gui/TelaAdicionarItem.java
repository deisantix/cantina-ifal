package br.com.cantinaifal.gui;

import javax.swing.*;
import java.awt.Color;
import java.awt.event.*;
import br.com.cantinaifal.estoque.Estoque;


public class TelaAdicionarItem extends JFrame {

    private Estoque estoque;

    private JOptionPane message;
    private JDialog dialog;

    private JLabel lblDescricao;
    private JLabel lblPrecoCompra;
    private JLabel lblPrecoVenda;
    private JLabel lblQuantidadeComprada;
    private JLabel lblEstoqueMinimo;
    
    private JTextField txtDescricao;
    private JTextField txtPrecoCompra;
    private JTextField txtPrecoVenda;
    private JTextField txtQuantidadeComprada;
    private JTextField txtEstoqueMinimo;

    private JButton btnAdicionar;
    private JButton btnCancelar;
    
    public TelaAdicionarItem(Estoque estoque) {
        this.estoque = estoque;
        this.message = new JOptionPane();

        setSize(480, 350);
        setTitle("Adicionar Item");
        setVisible(true);
        setResizable(false);
        setLayout(null);
        setLocationRelativeTo(null);

        this.lblDescricao = new JLabel("Descrição:");
        this.lblDescricao.setBounds(
            20,
            10,
            200, 20
        );
        add(this.lblDescricao);

        this.txtDescricao = new JTextField();
        this.txtDescricao.setBounds(
            20,
            40,
            200, 30
        );
        add(this.txtDescricao);


        this.lblPrecoCompra = new JLabel("Preço de compra:");
        this.lblPrecoCompra.setBounds(
            20,
            80,
            200, 20
        );
        add(this.lblPrecoCompra);

        this.txtPrecoCompra = new JTextField();
        this.txtPrecoCompra.setBounds(
            20,
            110,
            200, 30
        );
        add(this.txtPrecoCompra);


        this.lblPrecoVenda = new JLabel("Preço de venda:");
        this.lblPrecoVenda.setBounds(
            250,
            80,
            200, 20
        );
        add(this.lblPrecoVenda);

        this.txtPrecoVenda = new JTextField();
        this.txtPrecoVenda.setBounds(
            250,
            110,
            200, 30
        );
        add(this.txtPrecoVenda);


        this.lblQuantidadeComprada = new JLabel("Quantidade comprada:");
        this.lblQuantidadeComprada.setBounds(
            20,
            150,
            200, 20
        );
        add(this.lblQuantidadeComprada);

        this.txtQuantidadeComprada = new JTextField();
        this.txtQuantidadeComprada.setBounds(
            20,
            180,
            200, 30
        );
        add(this.txtQuantidadeComprada);


        this.lblEstoqueMinimo = new JLabel("Estoque mínimo:");
        this.lblEstoqueMinimo.setBounds(
            250,
            150,
            200, 20
        );
        add(this.lblEstoqueMinimo);

        this.txtEstoqueMinimo = new JTextField();
        this.txtEstoqueMinimo.setBounds(
            250,
            180,
            200, 30
        );
        add(this.txtEstoqueMinimo);


        this.btnAdicionar = new JButton("ADICIONAR");
        this.btnAdicionar.setBounds(
            80,
            260,
            150, 30
        );
        this.btnAdicionar.setFocusPainted(false);
        add(this.btnAdicionar);

        this.btnCancelar = new JButton("CANCELAR");
        this.btnCancelar.setBounds(
            240,
            260,
            150, 30
        );
        this.btnCancelar.setFocusPainted(false);
        add(this.btnCancelar);

        setBackground(Color.WHITE);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        repaint();


        this.btnAdicionar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String descricao = txtDescricao.getText();

                String textoPrecoCompra = txtPrecoCompra.getText();
                double precoCompra = 0;
                try {
                    precoCompra = Double.parseDouble(textoPrecoCompra);
                } catch (Exception ex) {
                    message.setMessage("Preco de compra inválido!");
                    dialog = message.createDialog(null, "Alerta");
                    dialog.setVisible(true);

                    return;
                }

                String textoPrecoVenda = txtPrecoVenda.getText();
                double precoVenda = 0;
                try {
                    precoVenda = Double.parseDouble(textoPrecoVenda);
                } catch (Exception ex) {
                    message.setMessage("Preco de venda inválido!");
                    dialog = message.createDialog(null, "Alerta");
                    dialog.setVisible(true);

                    return;
                }

                String textoQuantidade = txtQuantidadeComprada.getText();
                int quantidadeComprada = 0;
                try {
                    quantidadeComprada = Integer.parseInt(textoQuantidade);
                } catch (Exception ex) {
                    message.setMessage("Quantidade comprada inválida!");
                    dialog = message.createDialog(null, "Alerta");
                    dialog.setVisible(true);

                    return;
                }

                String textoEstoqueMinimo = txtEstoqueMinimo.getText();
                int estoqueMinimo = 0;
                try {
                    estoqueMinimo = Integer.parseInt(textoEstoqueMinimo);
                } catch (Exception ex) {
                    message.setMessage("Estoque mínimo inválido!");
                    dialog = message.createDialog(null, "Alerta");
                    dialog.setVisible(true);

                    return;
                }

                try {
                    estoque.adicionarItem(descricao, precoCompra, precoVenda, quantidadeComprada, estoqueMinimo);
                } catch (Exception ex) {
                    message.setMessage(ex.getMessage());
                    dialog = message.createDialog(null, "Alerta");
                    dialog.setVisible(true);

                    return;
                }

                message.setMessage("Item adicionado com sucesso!");
                dialog = message.createDialog(null, "Sucesso");
                dialog.setVisible(true);

                txtDescricao.setText("");
                txtPrecoCompra.setText("");
                txtPrecoVenda.setText("");
                txtQuantidadeComprada.setText("");
                txtEstoqueMinimo.setText("");
            }
        });

        this.btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}
