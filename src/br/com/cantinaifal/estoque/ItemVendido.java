package br.com.cantinaifal.estoque;


public class ItemVendido {
    
    private int idItemVenda;
    private int codigoProduto;
    private double preco;
    private int quantidadeVendida;

    public ItemVendido(int idItemVenda, int codigoProduto, double preco, int quantidadeVendida) {
        this.idItemVenda = idItemVenda;
        this.codigoProduto = codigoProduto;
        this.preco = preco;
        this.quantidadeVendida = quantidadeVendida;
    }

    public int getIdItemVenda() {
        return idItemVenda;
    }

    public int getCodigoProduto() {
        return codigoProduto;
    }

    public double getPreco() {
        return preco;
    }

    public int getQuantidadeVendida() {
        return quantidadeVendida;
    }
}
