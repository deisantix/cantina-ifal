package br.com.cantinaifal.gui;

import javax.swing.*;
import br.com.cantinaifal.Config;
import java.awt.Color;

public class FuncionarioFrame extends MainFrame {
    
    private JLabel lblAdicionarItens;
    private JLabel lblBaixarVendidos;
    private JLabel lblOrdenadoPorNome;
    private JLabel lblOrdenadoPorQuantidade;
    private JLabel lblBaixaQuantidade;
    private JLabel lblLucro;

    private JButton btnAdicionarItens;
    private JButton btnBaixarVendidos;
    private JButton btnOrdenadoPorNome;
    private JButton btnOrdenadoPorQuantidade;
    private JButton btnBaixaQuantidade;
    private JButton btnLucro;

    public FuncionarioFrame(String setor, JLabel imgSetor, JButton mainButton, Color buttonColor) {
        super(setor, imgSetor, mainButton, buttonColor);

        int actionLabelWidth = 380;
        int actionLabelHeight = 25;
        int xActionLabel = 30;
        int yActionLabel = 250;
        int espacamento = 30;


        this.lblAdicionarItens = new JLabel("1) Adicionar itens ao estoque");
        this.lblAdicionarItens.setBounds(
            xActionLabel, 
            yActionLabel, 
            actionLabelWidth, actionLabelHeight
        );
        add(this.lblAdicionarItens);

        this.btnAdicionarItens = new JButton("...");
        this.btnAdicionarItens.setBounds(
            actionLabelWidth - xActionLabel, 
            (yActionLabel + 4), 
            30, 17
        );
        this.btnAdicionarItens.setBackground(new java.awt.Color(120, 230, 255));
        add(this.btnAdicionarItens);


        int yBaixarVendidos = (yActionLabel + espacamento);
        this.lblBaixarVendidos = new JLabel("2) Dar baixa nos itens vendidos");
        this.lblBaixarVendidos.setBounds(
            xActionLabel, 
            yBaixarVendidos, 
            actionLabelWidth, actionLabelHeight
        );
        add(this.lblBaixarVendidos);

        this.btnBaixarVendidos = new JButton("...");
        this.btnBaixarVendidos.setBounds(
            actionLabelWidth - xActionLabel, 
            (yBaixarVendidos + 4), 
            30, 17
        );
        this.btnBaixarVendidos.setBackground(new Color(120, 230, 255));
        add(this.btnBaixarVendidos);


        int yOrdenadoPorNome = (yActionLabel + espacamento * 2);
        this.lblOrdenadoPorNome = new JLabel("3) Ver produtos ordenados por nome");
        this.lblOrdenadoPorNome.setBounds(
            xActionLabel,
            yOrdenadoPorNome,
            actionLabelWidth, actionLabelHeight
        );
        add(this.lblOrdenadoPorNome);

        this.btnOrdenadoPorNome = new JButton("...");
        this.btnOrdenadoPorNome.setBounds(
            actionLabelWidth - xActionLabel,
            (yOrdenadoPorNome + 4),
            30, 17
        );
        add(this.btnOrdenadoPorNome);


        int yOrdenadoPorQuantidade = (yActionLabel + espacamento * 3);
        lblOrdenadoPorQuantidade = new JLabel("4) Ver produtos ordenados por nome");
        this.lblOrdenadoPorQuantidade.setBounds(
            xActionLabel,
            yOrdenadoPorQuantidade,
            actionLabelWidth, actionLabelHeight
        );
        add(this.lblOrdenadoPorQuantidade);

        this.btnOrdenadoPorQuantidade = new JButton("...");
        this.btnOrdenadoPorQuantidade.setBounds(
            actionLabelWidth - xActionLabel,
            (yOrdenadoPorQuantidade + 4),
            30, 17
        );
        add(this.btnOrdenadoPorQuantidade);


        int yBaixaQuantidade = (yActionLabel + espacamento * 4);
        this.lblBaixaQuantidade = new JLabel("5) Visualizar produtos em baixa quantidade");
        this.lblBaixaQuantidade.setBounds(
            xActionLabel, 
            yBaixaQuantidade, 
            actionLabelWidth, actionLabelHeight
        );
        add(this.lblBaixaQuantidade);

        this.btnBaixaQuantidade = new JButton("...");
        this.btnBaixaQuantidade.setBounds(
            actionLabelWidth - xActionLabel, 
            (yBaixaQuantidade + 4), 
            30, 17
        );
        this.btnBaixaQuantidade.setBackground(new Color(120, 230, 255));
        add(this.btnBaixaQuantidade);


        int yLucro = (yActionLabel + espacamento * 5);
        this.lblLucro = new JLabel("6) Visualizar resumo de lucro");
        this.lblLucro.setBounds(
            xActionLabel, 
            yLucro, 
            actionLabelWidth, actionLabelHeight
        );
        add(this.lblLucro);

        this.btnLucro = new JButton("...");
        this.btnLucro.setBounds(
            actionLabelWidth - xActionLabel, 
            (yLucro + 4), 
            30, 17
        );
        this.btnLucro.setBackground(new Color(120, 230, 255));
        add(this.btnLucro);
    }

    public static void main(String[] args) {
        String setor = "Funcionário";

        Icon adm = new ImageIcon(Config.currentDir + "/assets/imgs/adm.png");
        JLabel imgSetor = new JLabel(adm);

        JButton mainButton = new JButton("DESCONECTAR");
        Color buttonColor = new Color(0, 90, 190);
        FuncionarioFrame ffm = new FuncionarioFrame(setor, imgSetor, mainButton, buttonColor);
    }

}
