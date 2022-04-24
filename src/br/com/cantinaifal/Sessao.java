package br.com.cantinaifal;

import br.com.cantinaifal.estoque.*;
import java.util.*;

public class Sessao {

    private Estoque estoque;
    private Scanner inputUsuario;
    private boolean naSessao = false;
    
    public Sessao() {
        this.estoque = new Estoque();
        this.inputUsuario = new Scanner(System.in);
    }

    public void iniciarSessao() {
        this.naSessao = true;

        while(naSessao) {
            this.dormirAlgumTempo();
            if(this.estoque.getAcessoAdmin()) { // se o administrador estiver com acesso
                try {
                    this.entrarAdmin();

                } catch(IllegalArgumentException e) {
                    System.out.println("Opção inválida");

                } catch(InputMismatchException e) {
                    System.out.println("Opção inválida");
                    this.inputUsuario.next();
                }

            } else { // caso o administrador não esteja com acesso
                try {
                    this.entrarCliente();

                } catch(IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                    
                } catch(InputMismatchException e) {
                    System.out.println("Opção inválida");
                    this.inputUsuario.next();
                }
            }
        }
        this.inputUsuario.close();
    }

    private void entrarCliente() {
        System.out.println(
            "\n" +
            "Bem-vindo à Cantina do IFAL!\n" +
            "Você está como CLIENTE\n\n" +

            "1) Ver produtos ordenados por nome\n" +
            "2) Ver produtos ordenados por quantidade\n" +
            "3) Comprar um produto\n" +
            "4) Entrar como Administrador\n" +
            "5) Parar o programa\n"
        );

        System.out.print("O que deseja fazer? ");
        int opcao = this.inputUsuario.nextInt();
        this.inputUsuario.nextLine(); // apenas para consumir resíduos de nextInt

        System.out.println(); // pular uma linha

        if(opcao <= 0 || opcao > 5) {
            throw new IllegalArgumentException("Opção inválida");
        }
        this.dormirAlgumTempo();

        switch(opcao) {
            case 1: // Ver produtos ordenados por nome
                try {
                    Map<String, Integer> estoqueMapa = this.estoque.retornarEstoquePorNome();
                    this.mostrarItensNoEstoque(estoqueMapa);

                } catch(NullPointerException e) {
                    System.out.println(e.getMessage());
                }
                break;

            case 2: // Ver produtos ordenados por quantidade
                try {
                    Map<String, Integer> ordenadoPorQuant = this.estoque.retornarEstoquePorQuantidade();
                    this.mostrarItensNoEstoque(ordenadoPorQuant);

                } catch(NullPointerException e) {
                    System.out.println(e.getMessage());
                }
                break;

            case 3: // Comprar um produto
                Map<String, Integer> ordenadoPorQuant = this.estoque.retornarEstoquePorQuantidade();
                this.mostrarItensNoEstoque(ordenadoPorQuant);

                Map<String, ArrayList<Item>> itensNoEstoque = this.estoque.getEstoqueMapa();

                System.out.print("\nDigite um item do catálogo (N para cancelar): ");
                String itemEscolhido = this.inputUsuario.nextLine();

                if(itemEscolhido.equals("N")) {
                    return;
                }

                this.dormirAlgumTempo();
                if(itensNoEstoque.get(itemEscolhido) == null) {
                    throw new IllegalArgumentException("\nItem não encontrado no estoque!");
                } 
                System.out.print("Quantos você deseja comprar: ");
                int quantidadeEscolhida = this.inputUsuario.nextInt();

                this.dormirAlgumTempo();
                if(
                    quantidadeEscolhida > itensNoEstoque.get(itemEscolhido).size() ||
                    quantidadeEscolhida <= 0
                ) {
                    throw new IllegalArgumentException("\nQuantidade inválida!");
                }

                this.estoque.comprarItemNoEstoque(itemEscolhido, quantidadeEscolhida);

                this.dormirAlgumTempo();
                System.out.println("\nItem(ns) vendidos!");
                break;

            case 4: // Entrar como Administrador
                if(!estoque.getTemAdmin()) { // se a senha não foi definida
                    try {
                        estoque.setSenhaAdmin(this.inputUsuario);

                    } catch(IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                }
                this.dormirAlgumTempo();
                
                if(estoque.getTemAdmin()) { // caso já tenha senha definida
                    try {
                        estoque.entrarComoAdmin(this.inputUsuario);
    
                    } catch(IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                }
                break;
            
            case 5: // Parar o programa
                this.naSessao = false;
                break;
        }
        this.dormirAlgumTempo();
    }

    private void entrarAdmin() {
        System.out.println(
            "\n" +
            "Bem-vindo à Cantina do IFAL!\n" +
            "Você está como ADMINISTRADOR\n"
        );

        if(this.estoque.getItensVendidos().size() != 0) {
            this.dormirAlgumTempo();
            System.out.println("EXISTEM ITENS A SEREM BAIXADOS\n");

            this.dormirAlgumTempo();
        }

        System.out.println(
            "1) Adicionar itens ao estoque\n" +
            "2) Dar baixa nos itens vendidos\n" +
            "3) Ver produtos ordenados por nome\n" +
            "4) Ver produtos ordenados por quantidade\n" +
            "5) Visualizar produtos em baixa quantidade\n" +
            "6) Visualizar resumo de lucro\n" +
            "7) Deslogar\n" +
            "8) Mudar senha de administrador\n" +
            "9) Parar o programa\n"
        );

        System.out.print("O que deseja fazer? ");
        int opcao = this.inputUsuario.nextInt();
        this.inputUsuario.nextLine(); // apenas para consumir resíduos de nextInt

        System.out.println();
        
        if(opcao <= 0 || opcao > 9) {
            throw new IllegalArgumentException();
        }
        this.dormirAlgumTempo();

        switch(opcao) {
            case 1: // Adicionar itens ao estoque
                try {
                    System.out.print("Nome: ");
                    String nomeProduto = this.inputUsuario.nextLine();

                    System.out.print("Descrição: ");
                    String descricaoProduto = this.inputUsuario.nextLine();

                    System.out.print("Por quanto foi comprado: ");
                    double precoCompraProduto = this.inputUsuario.nextDouble();

                    System.out.print("Por quanto será revendido: ");
                    double precoVendaProduto = this.inputUsuario.nextDouble();

                    System.out.print("Quantidade à adicionar: ");
                    int quantidadeProduto = this.inputUsuario.nextInt();

                    if(quantidadeProduto == 1) {
                        this.estoque.adicionarItem(
                            nomeProduto, 
                            descricaoProduto,
                            precoCompraProduto, 
                            precoVendaProduto
                        );
                    } else {
                        this.estoque.adicionarItem(
                            nomeProduto, 
                            descricaoProduto,
                            precoCompraProduto, 
                            precoVendaProduto,
                            quantidadeProduto
                        );
                    }
                    System.out.println("\nItem(ns) adicionado(s) com sucesso!\n");
                    
                } catch (IllegalArgumentException e) {
                    this.dormirAlgumTempo();
                    System.out.println("\nNão foi possível adicionar o(s) item(ns) no estoque");

                    if(e.getMessage() != "") {
                        System.out.println(e.getMessage());
                    }
                } catch(InputMismatchException e) {
                    this.dormirAlgumTempo();
                    System.out.println("\nNão foi possível adicionar o(s) item(ns) no estoque");

                    this.inputUsuario.next();
                }

                break;

            case 2: // Dar baixa nos itens vendidos
                if(this.estoque.getItensVendidos().size() == 0) {
                    System.out.println("Não há itens a serem baixados...");
                    return;
                }
                
                System.out.println("Itens vendidos no total:\n");

                for(String itemVendido : this.estoque.getItensVendidos().keySet()) {
                    System.out.println(
                        itemVendido + ": " + 
                        this.estoque.getItensVendidos().get(itemVendido).size() + " x " + 
                        this.estoque.getItensVendidos().get(itemVendido).get(0).getPrecoVenda()
                    );
                }
                System.out.print("\nDeseja dar baixa? (S/N): ");
                String decisao = this.inputUsuario.nextLine();
                this.dormirAlgumTempo();

                if(decisao.equals("S")) {
                    this.estoque.baixarItens();
                    System.out.println("Item(ns) baixados com sucesso!");
                    
                } else if(decisao.equals("N")) {
                    return;
                } else {
                    throw new IllegalArgumentException("Não é uma opção!");
                }

                break;

            case 3: // Ver produtos ordenados por nome
                try {
                    Map<String, Integer> estoqueMapa = this.estoque.retornarEstoquePorNome();
                    this.mostrarItensNoEstoque(estoqueMapa);

                } catch(NullPointerException e) {
                    System.out.println(e.getMessage());
                }
                break;

            case 4: // Ver produtos ordenados por quantidade
                try {
                    Map<String, Integer> ordenadoPorQuant = this.estoque.retornarEstoquePorQuantidade();
                    this.mostrarItensNoEstoque(ordenadoPorQuant);

                } catch(NullPointerException e) {
                    System.out.println(e.getMessage());
                }
                break;

            case 5: // Visualizar produtos em baixa quantidade
                try {
                    Map<String, Integer> ordenadoPorBaixaQuant = this.estoque.retornarEstoqueEmBaixaQuantidade();
                    this.mostrarItensNoEstoque(ordenadoPorBaixaQuant);

                } catch(NullPointerException e) {
                    System.out.println(e.getMessage());
                }
                break;

            case 6: // Visualizar resumo de lucro
                try {
                    double totalLucro = 0;

                    Map<String, ArrayList<Item>> resumoLucro = this.estoque.retornarResumoLucro();
                    for(String item : resumoLucro.keySet()) {
                        totalLucro += resumoLucro.get(item).get(0).getPrecoVenda() * resumoLucro.get(item).size();

                        System.out.println(
                            item + ": " +
                            resumoLucro.get(item).size()
                        );
                    }
                    System.out.println("Total vendido: R$" + totalLucro);

                } catch(NullPointerException e) {
                    System.out.println(e.getMessage());
                }
                break;

            case 7: // Deslogar
                this.estoque.deslogarComoAdmin();
                break;

            case 8: // Mudar senha de administrador
                try {
                    estoque.setSenhaAdmin(this.inputUsuario);

                } catch(IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
                this.dormirAlgumTempo();
                break;
        
            case 9: // Parar o programa
                this.naSessao = false;
                break;
        }
        this.dormirAlgumTempo();
    }

    private void dormirAlgumTempo() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            this.naSessao = false;
        }
    }

    private void mostrarItensNoEstoque(Map<String, Integer> mapaOrdenado) {
        for(String item : mapaOrdenado.keySet()) {
            System.out.println(
                item + ": " +
                mapaOrdenado.get(item)
            );
        }
    }

}
