package br.com.cantinaifal.estoque;

import java.util.*;
import java.util.stream.*;


public class Estoque {

    /**
     * CLASSE Estoque QUE CONTROLA A TODOS OS ITENS CRIADOS
     * COM MAPAS E LISTAS, ALÉM DE PROPRIEDADES DE ADMNISTRADOR
     * CONTÉM COMPORTAMENTOS RESPONSÁVEIS PARA MANIPULAR OS ITENS
     */

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
        // construtor de Estoque
        
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

    // getters

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

    // métodos para manipular admnistrador

    public void setSenhaAdmin(Scanner inputUsuario) throws IllegalArgumentException {
        // RESPONSÁVEL POR SETAR A SENHA DE ADMINISTRADOR

        // caso senha já tenha sido definida (temAdmin = true)
        // precisará passar por verificação para que a senha possa ser redefinida
        if(this.temAdmin) {
            System.out.print("Digite a senha atual para prosseguir: ");
            String senhaAtual = inputUsuario.nextLine();

            if(!senhaAtual.equals(this.senhaAdmin)) {
                throw new IllegalArgumentException("As senhas não correspondem!");
            }
        }
        // digitando nova senha
        System.out.print("Digite a nova senha: ");
        String senhaNova = inputUsuario.nextLine();

        // caso a senha não tenha 4 dígitos de apenas números, lance uma exceção
        if(!senhaNova.matches("[0-9]{4}")) {
            throw new IllegalArgumentException(
                "Senha inválida!" +
                "\nDigite uma senha de 4 dígitos contendo apenas números."
            );
        }
        // definindo nova senha
        this.senhaAdmin = senhaNova;
        System.out.println("Senha cadastrada com sucesso!\n");
        // setando temAdmin para true, pois a senha foi definida
        this.temAdmin = true;
    }

    public void entrarComoAdmin(Scanner inputUsuario) throws IllegalArgumentException {
        // método para autenticar o acesso de administrador pedindo a senha
        // caso a senha seja autenticada, a propriedade this.acessoAdmin se torna true
        // para mostrar que o admin agora tem acesso
        // caso contrário, exceção será lançada

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
        // método para deslogar o administrador
        // é necessário apenas setar this.acessoAdmin para false

        this.acessoAdmin = false;
        System.out.println("O admnistrador foi deslogado");
    }

    // métodos para manipular itens

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

        // se o item criado já não estiver no mapa, então crie uma chave para ele
        // com uma ArrayList vazia dentro (onde serão armazenados os itens)
        if(this.estoqueMapa.get(nomeItem) == null) {
            ArrayList<Item> listaItem = new ArrayList<>();
            this.estoqueMapa.put(nomeItem, listaItem);
        }
        // de qualquer forma, adicione os itens em suas respectivas listas
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

    public void adicionarItem(String nome, int quantidade) {
        // outro método adicionarItem usando overloading
        // para permitir criar um item já existente sem que precise
        // repetir todas suas informações
        // OBS.: até porque não faz sentido criar um mesmo item com propriedades diferentes (não nesse caso)

        this.adicionarItem(
            nome, 
            this.estoqueMapa.get(nome).get(0).getDescricao(), 
            this.estoqueMapa.get(nome).get(0).getPrecoCompra(), 
            this.estoqueMapa.get(nome).get(0).getPrecoVenda(),
            quantidade
        );
    }

    public Map<String, ArrayList<Item>> retornarEstoquePorNome() throws NullPointerException {
        // mostra os itens disponíveis no estoque por nome
        
        // caso o estoque esteja vazio
        if(this.estoqueMapa.size() == 0) {
            throw new NullPointerException("Parece que o estoque está vazio...");
        }
        // criando um novo mapa que será ordenado por nome
        // será usado TreeMap, que já ordena suas chaves automaticamente
        // pelo alfabeto e por números
        Map<String, ArrayList<Item>> novoMapa = new TreeMap<>();
        
        // preenchendo novoMapa
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

        // caso o estoque esteja vazio
        if(this.estoqueMapa.size() == 0) {
            throw new NullPointerException("Parece que o estoque está vazio...");
        }
        // criando um novo mapa que será ordenado por quantidade
        // esse mapa é temporário
        // tem como valores o tipo Integer, para que possa ser ordenado mais tarde pelos valores
        Map<String, Integer> mapaQuantidade = new HashMap<>();
        
        // populando mapaQuantidade
        // ao invés de colocar a lista inteira como valores
        // é colocada seu tamanho (que é equivalente à quantidade dos itens)
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
        // em um mapa <String, ArrayList<Item>> para que o código possa funcionar bem
        // pois fica mais complicado usar só Integer
        // é usado LinkedHashMap para que a ordem de itens adicionados seja mantida
        Map<String, ArrayList<Item>> novoMapaOrdenado = new LinkedHashMap<>();
        
        // populando novoMapaOrdenado com os itens de mapaOrdenado
        // porém pegando itens de this.estoqueMapa
        // pois mapaOrdenado não possui as listas, e sim os tamanhos
        for(String itemOrdenado : mapaOrdenado.keySet()) {
            novoMapaOrdenado.put(
                itemOrdenado,
                this.estoqueMapa.get(itemOrdenado)
            );
        }

        return novoMapaOrdenado;
    }
    
    public Map<String, ArrayList<Item>> retornarEstoqueEmBaixaQuantidade() throws NullPointerException {
        // método para retornar itens em quantidade abaixo de 50

        // caso o estoque esteja vazio
        if(this.estoqueMapa.size() == 0) {
            throw new NullPointerException("Parece que o estoque está vazio...");
        }

        // retornando mapa ordenado por quantidade
        Map<String, ArrayList<Item>> mapaOrdenado = this.retornarEstoquePorQuantidade();
        // instanciando LinkedHashMap para que a ordem de adição seja mantida
        Map<String, ArrayList<Item>> novoMapa = new LinkedHashMap<>();

        // populando novoMapa apenas se a quantidade dos itens seja menor ou igual a 50
        for(String itemBQ : mapaOrdenado.keySet()) {
            if(mapaOrdenado.get(itemBQ).size() <= 50) {
                novoMapa.put(itemBQ, mapaOrdenado.get(itemBQ));
            }
        }
        return novoMapa;
    }

    public double retornarLucroResumo() throws NullPointerException {
        // método que passa por todos os itens baixados
        // os imprime, e calcula a soma de seus valores (lucro)
        // com base nos preços de venda dos produtos


        // caso nenhum item tenha sido baixado
        if(this.itensBaixados.size() == 0) {
            throw new NullPointerException("Não foi dado baixa em nenhum item no estoque...");
        }
        
        double totalLucro = 0;
        for(String item : itensBaixados.keySet()) {
            totalLucro += itensBaixados.get(item).get(0).getPrecoVenda() * itensBaixados.get(item).size();

            System.out.println(
                item + ": " +
                itensBaixados.get(item).size()
            );
        }

        return totalLucro;
    }

    public void comprarItemNoEstoque(String itemEscolhido, int quantidadeEscolhida) {
        // lógica que transfere os itens comprados de um mapa a outro

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
