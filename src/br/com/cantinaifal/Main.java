package br.com.cantinaifal;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {
        Sessao sessao = new Sessao();
        sessao.iniciarSessao();
    }
}