package br.com.cantinaifal;

import java.util.ArrayList;

public class Estoque {

    private ArrayList<Item> estoque;

    public Estoque() {
        estoque = new ArrayList<Item>();
    }

    public void adicionarItem(Item novoItem) {
        this.estoque.add(novoItem);
    }   

    public void mostrarEstoque() {
        ArrayList<String> estoqueNomes = new ArrayList<String>();
        for(int i = 0; i < this.estoque.size(); i++) {
            estoqueNomes.add(this.estoque.get(i).getNome());
        }
        System.out.println(estoqueNomes);
    }
    
    public void baixarItem(String nomeDoItem) {
        for(int i = 0; i < this.estoque.size(); i++) {
            if (nomeDoItem == this.estoque.get(i).getNome()) {
                estoque.remove(i);
                break;
            }
        }
    }
    
}

    /**
     * REQUISITOS:
     * - PERMITIR QUE A CLASSE Estoque INSTANCIE OBJETOS Item E
     * OS GUARDE EM UMA ArrayList
     * - PERMITIR QUE A CLASSE Estoque IMPRIMA TODOS OS OBJETOS
     * Item DISPONÍVEIS NA ArrayList
     * - PERMITIR QUE UM ITEM SEJA BAIXADO (apenas retirá-lo da
     * a ArrayList quando for comprado já é suficiente por enquanto)
     */