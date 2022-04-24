package br.com.cantinaifal.estoque;

import java.util.*;
import java.util.stream.*;


public class Estoque {
    // coleção que armazena todos os itens do estoque
    private Map<String, ArrayList<Item>> estoqueMapa;
    private Map<String, ArrayList<Item>> itensVendidos;
    private Map<String, ArrayList<Item>> itensBaixados;
    // atributo que armazena a senha
    private String senhaAdmin;
    // esse atributo é usado para saber se há um administrador (tem a senha definida)
    private boolean temAdmin = false; 
    // esse atributo é para saber se a pessoa usando está com o acesso do admin
    private boolean acessoAdmin = false;


    public Estoque() {
        // construtor
        // TreeMap para que se fique mais fácil de ordenar na hora da impressão
        this.estoqueMapa = new HashMap<String, ArrayList<Item>>();
        this.itensVendidos = new HashMap<String, ArrayList<Item>>();
        this.itensBaixados = new HashMap<String, ArrayList<Item>>();

        // inicializando itens no estoque para que fique mais fácil de testar o programa
        this.adicionarItem("Agua", "Uma garrafinha de água", 2.50, 3.50, 10);
        this.adicionarItem("Coxinha", "Coxinha de frango", 3, 5, 3);
        this.adicionarItem("Amendoim", "500g de amendoim", 10, 12.50, 50);
        this.adicionarItem("Sanduiche", "Um sanduba para matar sua fome", 5, 6.70, 5);
        this.adicionarItem("Bolo de chocolate", "Uma fatia apenas", 3.50, 4.50, 12);

    }

    public Map<String, ArrayList<Item>> getEstoqueMapa() {
        return this.estoqueMapa;
    }

    public Map<String, ArrayList<Item>> getItensVendidos() {
        return this.itensVendidos;
    }

    public Map<String, ArrayList<Item>> getItensBaixados() {
        return this.itensBaixados;
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

    public Map<String, ArrayList<Item>> retornarEstoquePorNome() throws NullPointerException {
        // mostra os itens disponíveis no estoque por nome
        
        if(this.estoqueMapa.size() == 0) {
            throw new NullPointerException("Parece que o estoque está vazio...");
        }
        Map<String, ArrayList<Item>> novoMapa = new TreeMap<>();
        
        for(String itemNoEstoque : this.estoqueMapa.keySet()) {
            novoMapa.put(
                itemNoEstoque, 
                this.estoqueMapa.get(itemNoEstoque)
            );
        }

        return novoMapa;
    }

    public Map<String, ArrayList<Item>> retornarEstoquePorQuantidade() throws NullPointerException {
        // mostra os itens disponíveis no estoque por quantidade

        if(this.estoqueMapa.size() == 0) {
            throw new NullPointerException("Parece que o estoque está vazio...");
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

        // outro for loop para colocar todos os itens ordenados
        // em um mapa <String, ArrayList<Item>> para o código poder funcionar bem
        // pois fica mais complicado usar só Integer
        Map<String, ArrayList<Item>> novoMapaOrdenado = new LinkedHashMap<>();
        
        for(String itemOrdenado : mapaOrdenado.keySet()) {
            novoMapaOrdenado.put(
                itemOrdenado,
                this.estoqueMapa.get(itemOrdenado)
            );
        }

        return novoMapaOrdenado;
    }
    
    public Map<String, ArrayList<Item>> retornarEstoqueEmBaixaQuantidade() throws NullPointerException {
        
        if(this.estoqueMapa.size() == 0) {
            throw new NullPointerException("Parece que o estoque está vazio...");
        }
        Map<String, ArrayList<Item>> mapaOrdenado = this.retornarEstoquePorQuantidade();
        Map<String, ArrayList<Item>> novoMapa = new LinkedHashMap<>();

        for(String itemBQ : mapaOrdenado.keySet()) {
            if(mapaOrdenado.get(itemBQ).size() <= 50) {
                novoMapa.put(itemBQ, mapaOrdenado.get(itemBQ));
            }
        }
        return novoMapa;
    }

    public Map<String, ArrayList<Item>> retornarResumoLucro() throws NullPointerException {
        if(this.itensBaixados.size() == 0) {
            throw new NullPointerException("Não foi dado baixa em nenhum item no estoque...");
        }
        return this.itensBaixados;
    }

    public void comprarItemNoEstoque(String itemEscolhido, int quantidadeEscolhida) {
        if(this.itensVendidos.get(itemEscolhido) == null) {
            this.itensVendidos.put(itemEscolhido, new ArrayList<Item>());
        }
        // primeiro for loop para transferir os itens de uma lista para outra
        for(int i = 0; i < quantidadeEscolhida; i++) {
            Item itemTransferido = this.estoqueMapa.get(itemEscolhido).get(i);
            this.itensVendidos.get(itemEscolhido).add(itemTransferido);
        }
        // segundo for loop para remover os itens que foram vendidod de estoqueMapa
        for(int i = 0; i < quantidadeEscolhida; i++) {
            this.estoqueMapa.get(itemEscolhido).remove(0);

            if(this.estoqueMapa.get(itemEscolhido).size() == 0) {
                this.estoqueMapa.remove(itemEscolhido);
            }
        }
    }

    public void baixarItens() {
        // baixa um item

        // primeiro for loop para transferir os itens de uma lista para outra
        for(String item : this.itensVendidos.keySet()) {
            ArrayList<Item> itensTransferidos = this.itensVendidos.get(item);
            this.itensBaixados.put(item, itensTransferidos);
        }
        // segundo for loop para remover os itens de itensVendidos
        // usando itensBaixados para iterar para evitar ConcurrentModificationException
        for(String item : this.itensBaixados.keySet()) {
            this.itensVendidos.remove(item);
        }
    }
    
}
