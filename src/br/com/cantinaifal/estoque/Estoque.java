package br.com.cantinaifal.estoque;

import java.util.*;
import java.util.stream.*;


public class Estoque {
    // coleção que armazena todos os itens do estoque
    private Map<String, ArrayList<Item>> estoqueMapa;
    // atributo que armazena a senha
    private String senhaAdmin;
    // esse atributo é usado para saber se há um administrador (tem a senha definida)
    private boolean temAdmin = false; 
    // esse atributo é para saber se a pessoa usando está com o acesso do admin
    private boolean acessoAdmin = false;

    public Estoque() {
        // construtor
        // TreeMap para que se fique mais fácil de ordenar na hora da impressão
        this.estoqueMapa = new TreeMap<String, ArrayList<Item>>();
    }


    public boolean getTemAdmin() {
        return this.temAdmin;
    }

    public boolean getAcessoAdmin() {
        return this.acessoAdmin;
    }

    public void setSenhaAdmin(Scanner inputUsuario) throws IllegalArgumentException {
        if(this.temAdmin) {
            System.out.print("Digite a senha atual para prosseguir: ");
            String senhaAtual = inputUsuario.nextLine();

            if(!senhaAtual.equals(this.senhaAdmin)) {
                throw new IllegalArgumentException("As senhas não correspondem!");
            }
        }
        
        System.out.print("Digite a nova senha: ");
        String senhaNova = inputUsuario.nextLine();

        if(!senhaNova.matches("[0-9]{4}")) {
            throw new IllegalArgumentException(
                "Senha inválida!" +
                "\nDigite uma senha de 4 dígitos contendo apenas números."
            );
        }
        this.senhaAdmin = senhaNova;
        System.out.println("Senha cadastrada com sucesso!\n");

        this.temAdmin = true;
    }

    public void entrarComoAdmin(Scanner inputUsuario) throws IllegalArgumentException {
        System.out.print("Digite a senha do administrador: ");
        String senhaInput = inputUsuario.nextLine();

        if(senhaInput.equals(this.senhaAdmin)) {
            this.acessoAdmin = true;
            System.out.println("Admnistrador acessado com sucesso!");
        } else {
            throw new IllegalArgumentException("Não foi possível acessar o administrador!");
        }
    }

    public void deslogarComoAdmin() {
        this.acessoAdmin = false;
        System.out.println("O admnistrador foi deslogado");
    }

    public void adicionarItem(
        String nome, 
        String descricao, 
        double precoCompra, 
        double precoVenda
    ) throws IllegalArgumentException {
        // método adicionarItem que adiciona o item na coleção
        Item item = new Item(nome, descricao, precoCompra, precoVenda);

        // se o item for criado com sucesso
        // não precisa de if, pois o método é interrompido no momento do erro (caso tenha)
        String nomeItem = item.getNome();

        if(this.estoqueMapa.get(nomeItem) == null) {
            ArrayList<Item> listaItem = new ArrayList<>();
            this.estoqueMapa.put(nomeItem, listaItem);
        }
        this.estoqueMapa.get(nomeItem).add(item);
    }
    
    public void adicionarItem(
        String nome, 
        String descricao, 
        double precoCompra, 
        double precoVenda,
        int quantidade
    ) {
        // método adicionarItem que utiliza da técnica overloading
        // para poder adicionar dinamicamente uma certa quantidade de itens na lista

        for(int i = 0; i < quantidade; i++) {
            this.adicionarItem(nome, descricao, precoCompra, precoVenda);
        }
    }

    public void mostrarEstoquePorNome() {
        // mostra os itens disponíveis no estoque por nome
        if(this.estoqueMapa.size() == 0) {
            System.out.println("Parece que o estoque está vazio...");
            return;
        }

        for(String key : this.estoqueMapa.keySet()) {
            System.out.println(key + ": " + this.estoqueMapa.get(key).size());
        }

    }

    public void mostrarEstoquePorQuantidade() {
        // mostra os itens disponíveis no estoque por quantidade
        if(this.estoqueMapa.size() == 0) {
            System.out.println("Parece que o estoque está vazio...");
            return;
        }

        Map<String, Integer> mapaQuantidade = new HashMap<>();
        
        for(String item : this.estoqueMapa.keySet()) {
            mapaQuantidade.put(
                item,
                this.estoqueMapa.get(item).size()
            );
        }

        // Nota de Desenvolvedor: Ytalo, 23-04-2022
        // código baseado nas respostas dessa questão do StackOverflow:
        // https://stackoverflow.com/questions/109383/sort-a-mapkey-value-by-values
        // não sei o que é um stream, nem o que os operadores :: e -> fazem
        // mas pelo menos funciona

        Map<String, Integer> mapaOrdenado = mapaQuantidade
            .entrySet()
            .stream()
            .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
            .collect(Collectors.toMap(
                Map.Entry::getKey,
                Map.Entry::getValue,
                (e1, e2) -> e1, LinkedHashMap::new
            ));


        for(String item : mapaOrdenado.keySet()) {
            System.out.println(item + ": " + mapaOrdenado.get(item));
        }
    }
    
    public void baixarItem(String nomeDoItem) {
        // baixa um item
    }
    
}
