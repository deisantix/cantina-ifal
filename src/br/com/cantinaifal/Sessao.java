package br.com.cantinaifal;

import br.com.cantinaifal.estoque.Estoque;
import java.util.InputMismatchException;
import java.util.Scanner;

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
            if(this.estoque.getAcessoAdmin()) {
                try {
                    this.entrarAdmin();
                } catch (InputMismatchException | IllegalArgumentException e) {
                    System.out.println("Opção inválida");
                    this.inputUsuario.next();
                }

            } else {
                try {
                    this.entrarCliente();

                } catch(InputMismatchException | IllegalArgumentException e) {
                    System.out.println("Opção inválida");
                    this.inputUsuario.next();
                }
            }
        }
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

        System.out.println();

        if(opcao <= 0 || opcao > 5) {
            throw new IllegalArgumentException();
        }
        this.dormirAlgumTempo();

        switch(opcao) {
            case 1: // Ver produtos ordenados por nome
                this.estoque.mostrarEstoquePorNome();
                break;

            case 2: // Ver produtos ordenados por quantidade
                this.estoque.mostrarEstoquePorQuantidade();
                break;

            case 3: // Comprar um produto
                break;

            case 4: // Entrar como Administrador
                if(!estoque.getTemAdmin()) {
                    try {
                        estoque.setSenhaAdmin(this.inputUsuario);

                    } catch(IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                }
                this.dormirAlgumTempo();
                
                if(estoque.getTemAdmin()) {
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
            "Você está como ADMINISTRADOR\n\n" +

            "1) Adicionar itens ao estoque\n" +
            "2) Dar baixa nos itens vendidos\n" +
            "3) Ver produtos ordenados por nome\n" +
            "4) Ver produtos ordenados por quantidade\n" +
            "5) Visualizar produtos em baixa quantidade\n" +
            "6) Visualizar resumo de lucro\n" +
            "7) Deslogar\n" +
            "8) Parar o programa\n"
        );

        System.out.print("O que deseja fazer? ");
        int opcao = this.inputUsuario.nextInt();
        this.inputUsuario.nextLine(); // apenas para consumir resíduos de nextInt

        System.out.println();
        
        if(opcao <= 0 || opcao > 8) {
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
                break;

            case 3: // Ver produtos ordenados por nome
                this.estoque.mostrarEstoquePorNome();
                break;

            case 4: // Ver produtos ordenados por quantidade
                this.estoque.mostrarEstoquePorQuantidade();
                break;

            case 5: // Visualizar produtos em baixa quantidade
                break;

            case 6: // Visualizar resumo de lucro
                break;

            case 7: // Deslogar
                this.estoque.deslogarComoAdmin();
                break;
        
            case 8: // Parar o programa
                this.naSessao = false;
                break;
        }
        this.dormirAlgumTempo();
    }

    public void dormirAlgumTempo() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            this.naSessao = false;
        }
    }

}
