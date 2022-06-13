package br.com.cantinaifal;

import java.sql.*;
import javax.swing.*;
import java.awt.Color;
import br.com.cantinaifal.gui.ClienteFrame;

public class Main {
    public static void main(String[] args) throws SQLException {
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