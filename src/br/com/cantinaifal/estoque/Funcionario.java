package br.com.cantinaifal.estoque;

public class Funcionario {
    
    private int codigoLogin;
    private String login;
    private String senha;

    public Funcionario(int codigo, String login, String senha) {
        this.codigoLogin = codigo;
        this.setLogin(login);
        this.setSenha(senha);
    }


    public int getCodigoLogin() {
        return codigoLogin;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    
    public void setLogin(String login) {
        if(!login.matches("[a-zA-Z0-9_]{6,12}")) {
            throw new IllegalArgumentException("Estrutura de login inválida!");
        }
        this.login = login;
    }


    public void setSenha(String senha) {
        if(!senha.matches("[0-9]{4}")) {
            throw new IllegalArgumentException("Senha inválida!");
        }
        this.senha = senha;
    }

    public void setNovaSenha(String antigaSenha, String novaSenha) {
        if(antigaSenha != this.senha) {
            throw new IllegalArgumentException("Senhas não correspondem!");
        }
        this.setSenha(novaSenha);
    }

}
