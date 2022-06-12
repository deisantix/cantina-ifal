/**
 * CLASSE Item PARA A CRIAÇÃO DE ITENS NO ESTOQUE
 */

package br.com.cantinaifal.estoque;


public class Item {
    
    private int codigo;
    private String descricao;
    private double precoCompra;
    private double precoVenda;
    private int quantidadeComprada;
    private int estoqueMinimo;


    public Item(int codigo, String descricao, double precoCompra, double precoVenda, int quantidadeComprada, int estoqueMinimo) {
        // construtor da classe item que verifica os valores colocados na hora de instanciação
        
        int codigoLength = String.valueOf(codigo).length();
        if (codigoLength < 4) {
            throw new IllegalArgumentException("Código de item inválido");
        
        } else if (descricao.isEmpty()) {
            throw new IllegalArgumentException("Descrição inválida");
        }
        
        else if (precoCompra <= 0 || precoVenda <= 0) {
            throw new IllegalArgumentException("Preço de compra ou venda inválido");

        } else if (precoVenda < precoCompra) {
            throw new IllegalArgumentException("Preço de venda menor que preço de compra");

        } else if (quantidadeComprada <= 0) {
            throw new IllegalArgumentException("Quantidade comprada inválida");

        } else if (estoqueMinimo <= 0 || estoqueMinimo > quantidadeComprada) {
            throw new IllegalArgumentException("Estoque mínimo inválido ou maior que quantidade comprada");
        }

        this.codigo = codigo;
        this.descricao = descricao.toUpperCase();
        this.precoCompra = precoCompra;
        this.precoVenda = precoVenda;
        this.quantidadeComprada = quantidadeComprada;
        this.estoqueMinimo = estoqueMinimo;
    }

    // getters

    public int getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public double getPrecoCompra() {
        return this.precoCompra;
    }

    public double getPrecoVenda() {
        return this.precoVenda;
    }

    public int getQuantidadeComprada() {
        return quantidadeComprada;
    }

    public int getEstoqueMinimo() {
        return estoqueMinimo;
    }

}

