package br.com.cantinaifal.gui;


import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import br.com.cantinaifal.estoque.Estoque;


public abstract class MainFrame extends JFrame {

    protected Estoque estoque;

    protected int frameWidth = 800;
    protected int frameHeight = 600;

    private JLabel setor;
    private JLabel imgSetor;
    private JLabel sejaBemVindo;

    protected JScrollPane painelLateral;
    protected JButton mainButton;

    protected JOptionPane message;
    protected JDialog dialog;

    public MainFrame(String setor, JLabel imgSetor, JButton mainButton, Color buttonColor) {
        this.imgSetor = imgSetor;
        this.mainButton = mainButton;
        this.message = new JOptionPane();

        setSize(this.frameWidth, this.frameHeight);
        setTitle("Cantina IFAL");
        setVisible(true);
        setResizable(false);
        setLayout(null);
        setLocationRelativeTo(null);

        this.setor = new JLabel(setor, SwingConstants.CENTER);
        this.setor.setFont(new Font("Arial", Font.BOLD, 20));
        this.setor.setBounds(
            (this.frameWidth / 2) - (140 / 2),
            15,
            140, 25
        );
        add(this.setor);

        this.imgSetor.setBounds(
            (this.frameWidth / 2) - (128 / 2),
            40,
            128, 128
        );
        add(this.imgSetor);

        this.sejaBemVindo = new JLabel(
            "Bem-vindo à Cantina do IFAL! Você está como " + setor.toUpperCase(),
            SwingConstants.CENTER
        );
        this.sejaBemVindo.setBounds(
            (this.frameWidth / 2) - (500 / 2),
            170,
            500, 25
        );
        add(this.sejaBemVindo);

        this.mainButton.setBounds(
            (this.frameWidth - 190),
            10,
            180, 30
        );
        this.mainButton.setForeground(Color.WHITE);
        this.mainButton.setBackground(buttonColor);
        this.mainButton.setFocusPainted(false);
        add(this.mainButton);


        this.painelLateral = new JScrollPane();
        this.painelLateral.setLayout(null);
        this.painelLateral.setBounds(
            (this.frameWidth / 2) - (this.frameWidth - 20) / 2, 
            200, 
            (this.frameWidth - 20), 270
        );
        this.painelLateral.setVisible(true);
        // this.painelLateral.setBackground(Color.BLUE);
        add(this.painelLateral);

        setBackground(Color.WHITE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        repaint();
    }

}
