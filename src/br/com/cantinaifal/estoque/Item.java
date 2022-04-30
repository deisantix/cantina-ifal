package br.com.cantinaifal.estoque;

public class Item {

    /**
     * CLASSE Item PARA A CRIAÇÃO DE ITENS NO ESTOQUE
     */

    private String nome;
    private String descricao;
    private double precoCompra;
    private double precoVenda;

    public  Item(String nome, String descricao, double precoCompra, double precoVenda) {
        // construtor da classe item que verifica os valores colocados na hora de instanciação
        
        if(nome == "") {
            throw new IllegalArgumentException("Nome inválido");
        } else if (precoCompra <= 0 || precoVenda <= 0) {
            throw new IllegalArgumentException("Preço de compra ou venda inválido");
        } else if (precoVenda < precoCompra) {
            throw new IllegalArgumentException("Preço de venda menor que preço de compra");
        }

        this.nome = nome.toUpperCase();
        this.descricao = descricao;
        this.precoCompra = precoCompra;
        this.precoVenda = precoVenda;
    }

    // getters

    public String getNome() {
        return this.nome;
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

}

