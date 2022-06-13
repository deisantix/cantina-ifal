package br.com.cantinaifal.estoque.sql;


import java.sql.*;
import java.util.Map;

import com.mysql.cj.log.Log;

import br.com.cantinaifal.estoque.Funcionario;
import br.com.cantinaifal.estoque.Item;
import br.com.cantinaifal.estoque.ItemVendido;


public class EstoqueConsulta {

    PreparedStatement stmt;
    Connection connection;

    public EstoqueConsulta(Connection con) throws SQLException {
        this.connection = con;
    }

    public void insertProduto(Item item, Funcionario func) throws Exception {
        this.connection.setAutoCommit(false);
        String sqlProduto = "INSERT INTO cantinaifal.produto " +
                            "VALUES (?, ?, ?, ?, ?, ?)"; 
        
        String sqlCadastra = "INSERT INTO cantinaifal.cadastra " +
                             "VALUES (?, ?)";

        try {
            this.stmt = this.connection.prepareStatement(sqlProduto);

            this.stmt.setInt(1, item.getCodigo());
            this.stmt.setString(2, item.getDescricao());
            this.stmt.setDouble(3, item.getPrecoCompra());
            this.stmt.setDouble(4, item.getPrecoVenda());
            this.stmt.setInt(5, item.getQuantidadeComprada());
            this.stmt.setInt(6, item.getEstoqueMinimo());
            this.stmt.execute();

            this.stmt = this.connection.prepareStatement(sqlCadastra);

            this.stmt.setInt(1, func.getCodigoLogin());
            this.stmt.setInt(2, item.getCodigo());
            this.stmt.execute();

            this.connection.commit();
            
        } catch (Exception e) {
            this.connection.rollback();
            throw new Exception(e);
        }
    }

    public void insertItemVendido(ItemVendido item) {
        String sql = "INSERT INTO cantinaifal.itemvenda " +
                     "VALUES (?, ?, ?, ?)";
        try {
            this.stmt = this.connection.prepareStatement(sql);

            this.stmt.setInt(1, item.getIdItemVenda());
            this.stmt.setInt(2, item.getCodigoProduto());
            this.stmt.setDouble(3, item.getPreco());
            this.stmt.setInt(4, item.getQuantidadeVendida());

            this.stmt.execute();
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void insertFuncionario(Funcionario func) throws Exception {
        String sql = "INSERT INTO cantinaifal.funcionario " +
                     "VALUES (?, ?, ?)";
    
        this.stmt = this.connection.prepareStatement(sql);
        
        this.stmt.setInt(1, func.getCodigoLogin());
        this.stmt.setString(2, func.getLogin());
        this.stmt.setString(3, func.getSenha());

        this.stmt.execute();
    }

    public void updateQuantidadeProduto(int codigoItemEscolhido, int novaQuantidade) {
        String qryUpdate =  "UPDATE cantinaifal.produto " +
                            "SET quantidade_comprada = " + novaQuantidade + " " +
                            "WHERE codigo_produto = " + codigoItemEscolhido;
        try {
            this.stmt = this.connection.prepareStatement(qryUpdate);
            this.stmt.execute();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public ResultSet selectQuery(String sql) {
        ResultSet result;
        try {
            this.stmt = this.connection.prepareStatement(sql);
            result = this.stmt.executeQuery();
            
        } catch (Exception e) {
            System.out.println(e);
            result = null;
        }
        return result;
    }

    public ResultSet selectQuery(String sql, Map<Integer, Object> attr) {
        ResultSet result;
        try {
            this.stmt = this.connection.prepareStatement(sql);

            for(int key : attr.keySet()) {
                if (attr.get(key) instanceof String) {
                    this.stmt.setString(key, (String) attr.get(key));

                } else if (attr.get(key) instanceof Integer) {
                    this.stmt.setInt(key, (Integer) attr.get(key));

                } else if (attr.get(key) instanceof Double) {
                    this.stmt.setDouble(key, (Double) attr.get(key));
                }
            }

            result = this.stmt.executeQuery();
            
        } catch (Exception e) {
            System.out.println(e);
            result = null;
        }
        return result;
    }

    public void deleteColuna(String fromTabela, String where) {
        String sql = "DELETE FROM cantinaifal." + fromTabela + " " +
                     "WHERE " + where;
        try {
            this.stmt = this.connection.prepareStatement(sql);
            this.stmt.execute();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
