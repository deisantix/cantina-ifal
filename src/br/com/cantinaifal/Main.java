package br.com.cantinaifal;


public class Main {
    public static void main(String[] args) {
        Estoque estoque = new Estoque();
        estoque.mostrarEstoque();

        Item coxinha = new Item("Coxinha", "Coxinha de frango", 3, 5);
        estoque.adicionarItem(coxinha);
        estoque.mostrarEstoque();

        estoque.baixarItem("Coxinha");
        estoque.mostrarEstoque();
    }
}