package br.com.cantinaifal.gui;

import javax.swing.*;
import java.awt.Color;
import java.awt.event.*;
import br.com.cantinaifal.estoque.Estoque;
import br.com.cantinaifal.Config;

public class TelaLogin extends JFrame {

    private Estoque estoque;
    private FuncionarioFrame funcionarioFrame;

    private JOptionPane message;
    private JDialog dialog;

    private JLabel lblLogin;
    private JLabel lblSenha;
    private JLabel lblPerguntaNaoPossuiUsuario;
    private JTextField txtLogin;
    private JTextField txtSenha;

    private JPanel painelBotoes;

    private JButton btnEntrar;
    private JButton btnCadastrar;
    private JButton btnSalvarCadastro;
    private JButton btnCancelar;
    
    public TelaLogin(Estoque estoque, ClienteFrame cliente) {
        this.estoque = estoque;
        this.message = new JOptionPane();

        setSize(320, 350);
        setTitle("Login Funcionário");
        setVisible(true);
        setResizable(false);
        setLayout(null);
        setLocationRelativeTo(null);

        this.lblLogin = new JLabel("Login: ");
        this.lblLogin.setBounds(40, 40, 200, 20);
        add(this.lblLogin);

        this.txtLogin = new JTextField();
        this.txtLogin.setBounds(40, 60, 150, 30);
        add(this.txtLogin);

        this.lblSenha = new JLabel("Senha: ");
        this.lblSenha.setBounds(40, 100, 200, 20);
        add(this.lblSenha);

        this.txtSenha = new JTextField();
        this.txtSenha.setBounds(40, 120, 150, 30);
        add(this.txtSenha);

        this.painelBotoes = new JPanel();
        this.painelBotoes.setLayout(null);
        this.painelBotoes.setVisible(true);
        this.painelBotoes.setBounds(0, 150, 320, 200);
        // this.painelBotoes.setBackground(Color.BLUE);
        add(this.painelBotoes);

        this.btnEntrar = new JButton("ENTRAR");
        this.btnCancelar = new JButton("CANCELAR");
        this.btnSalvarCadastro = new JButton("SALVAR");
        this.btnCadastrar = new JButton("CADASTRAR");

        this.desenharFormularioLogin();

        this.btnEntrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String login = txtLogin.getText();
                String senha = txtSenha.getText();

                try {
                    estoque.entrarComoFuncionario(login, senha);
                    
                } catch (Exception ex) {
                    message.setMessage(ex.getMessage());
                    dialog = message.createDialog(null, "Alerta");
                    dialog.setVisible(true);

                    return;
                }

                message.setMessage("Login realizado com sucesso!");
                dialog = message.createDialog(null, "Sucesso");
                dialog.setVisible(true);

                cliente.dispose();

                String setor = "Funcionário";

                Icon adm = new ImageIcon(Config.currentDir + "/assets/imgs/adm.png");
                JLabel imgSetor = new JLabel(adm);

                JButton mainButton = new JButton("DESCONECTAR");
                Color buttonColor = new Color(0, 90, 190);

                funcionarioFrame = new FuncionarioFrame(setor, imgSetor, mainButton, buttonColor, estoque);
                dispose();
            }
        });

        this.btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        this.btnCadastrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                desenharFormularioCadastro();
            }
        });

        this.btnSalvarCadastro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String login = txtLogin.getText();
                String senha = txtSenha.getText();

                try {
                    estoque.cadastrarFuncionario(login, senha);
                    
                } catch (Exception ex) {
                    message.setMessage(ex.getMessage());
                    dialog = message.createDialog(null, "Alerta");
                    dialog.setVisible(true);

                    return;
                }

                message.setMessage("Cadastro realizado com sucesso!");
                dialog = message.createDialog(null, "Sucesso");
                dialog.setVisible(true);

                txtLogin.setText("");
                txtSenha.setText("");

                desenharFormularioLogin();
            }
        });

        setBackground(Color.WHITE);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        repaint();
    }

    public void desenharFormularioLogin() {
        this.painelBotoes.removeAll();

        this.lblLogin.setText("Login: ");
        this.lblSenha.setText("Senha: ");

        this.btnEntrar.setBounds(80, 20, 150, 30);
        this.btnEntrar.setFocusPainted(false);
        this.painelBotoes.add(this.btnEntrar);

        this.btnCancelar.setBounds(80, 60, 150, 30);
        this.btnCancelar.setFocusPainted(false);
        this.painelBotoes.add(this.btnCancelar);

        this.lblPerguntaNaoPossuiUsuario = new JLabel("Não possui usuário?");
        this.lblPerguntaNaoPossuiUsuario.setBounds(10, 110, 200, 20);
        this.painelBotoes.add(this.lblPerguntaNaoPossuiUsuario);

        this.btnCadastrar.setBounds(10, 130, 150, 30);
        this.btnCadastrar.setFocusPainted(false);
        this.painelBotoes.add(this.btnCadastrar);

        repaint();
    }

    public void desenharFormularioCadastro() {
        this.painelBotoes.removeAll();

        this.lblLogin.setText("Login (6 a 12 caracteres):");
        this.lblSenha.setText("Senha (4 digitos): ");

        this.btnSalvarCadastro.setBounds(80, 20, 150, 30);
        this.btnSalvarCadastro.setFocusPainted(false);
        this.painelBotoes.add(this.btnSalvarCadastro);

        this.btnCancelar.setBounds(80, 60, 150, 30);
        this.btnCancelar.setFocusPainted(false);
        this.painelBotoes.add(this.btnCancelar);

        this.painelBotoes.repaint();
    }
}
