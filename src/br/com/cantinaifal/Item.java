package br.com.cantinaifal;

public class Item {

    private String nome;
    private String descricao;
    private double precoCompra;
    private double precoVenda;
    // private int qtdComprada;
    // private int qtdVendida;

    public  Item(String nome, String descricao, double precoCompra, double precoVenda) {
        if(nome == null) {
            throw new IllegalArgumentException("Nome inválido");
        } else if (descricao == null) {
            throw new IllegalArgumentException("Descrição inválida");
        } else if (precoCompra <= 0) {
            throw new IllegalArgumentException("Preço de compra inválido");
        } else if (precoVenda < precoCompra) {
            throw new IllegalArgumentException("Preco de venda inválido");
        }

        this.nome = nome;
        this.descricao = descricao;
        this.precoCompra = precoCompra;
        this.precoVenda = precoVenda;
    }

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

