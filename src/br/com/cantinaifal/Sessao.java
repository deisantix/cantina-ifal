/**
 * CLASSE Sessao QUE DISPONIBILIZA UMA INTERFACE PARA A MANIPULAÇÃO DO ESTOQUE
 */

package br.com.cantinaifal;

import br.com.cantinaifal.estoque.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class Sessao {

    private Estoque estoque;
    private Scanner inputUsuario;
    private boolean naSessao = false;
    
    public Sessao() {
        // construtor
        try {
            this.estoque = new Estoque();
            
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Não foi possível iniciar o programa");
        }
        this.inputUsuario = new Scanner(System.in);
    }

    public void iniciarSessao() {
        // LOOP RESPONSÁVEL POR MANTER O PROGRAMA ATIVO
        this.naSessao = true;

        if(this.estoque != null) {

            while(naSessao) {
                this.dormirAlgumTempo();
    
                // se o administrador estiver com acesso
                if(this.estoque.getAcessoAdmin()) { 
                    try {
                        this.entrarAdmin();
    
                    } catch(IllegalArgumentException e) {
                        System.out.println("Opção inválida");
    
                    } catch(InputMismatchException e) {
                        System.out.println("Opção inválida");
                        this.inputUsuario.next();
                    }
                    
                // caso o administrador não esteja com acesso
                } else {
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

        }

        // fechar o Scanner quando o programa for encerrado
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
                    ResultSet produtos = this.estoque.retornarEstoquePorNome();
                    this.mostrarItensNoEstoque(produtos);

                } catch(SQLException e) {
                    System.out.println(e.getMessage());
                }
                break;

            case 2: // Ver produtos ordenados por quantidade
                try {
                    ResultSet produtos = this.estoque.retornarEstoquePorQuantidade();
                    this.mostrarItensNoEstoque(produtos);

                } catch(SQLException e) {
                    System.out.println(e.getMessage());
                }
                break;

            case 3: // Comprar um produto
                try {
                    this.comprarProduto();

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }

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

        // if(this.estoque.getItensVendidos().size() != 0) {
        //     this.dormirAlgumTempo();
        //     System.out.println("EXISTEM ITENS A SEREM BAIXADOS\n");

        //     this.dormirAlgumTempo();
        // }

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
                // this.adicionarItemAoEstoque();
                break;

            case 2: // Dar baixa nos itens vendidos
                // this.darBaixaEmItens();
                break;

            case 3: // Ver produtos ordenados por nome
                try {
                    ResultSet produtos = this.estoque.retornarEstoquePorNome();
                    this.mostrarItensNoEstoque(produtos);

                } catch(SQLException e) {
                    System.out.println(e.getMessage());
                }
                break;

            case 4: // Ver produtos ordenados por quantidade
                try {
                    ResultSet produtos = this.estoque.retornarEstoquePorQuantidade();
                    this.mostrarItensNoEstoque(produtos);

                } catch(SQLException e) {
                    System.out.println(e.getMessage());
                }
                break;

            case 5: // Visualizar produtos em baixa quantidade
                try {
                    ResultSet produtos = this.estoque.retornarEstoqueEmBaixaQuantidade();
                    this.mostrarItensNoEstoque(produtos);

                } catch(SQLException e) {
                    System.out.println(e.getMessage());
                }
                break;

            case 6: // Visualizar resumo de lucro
                // this.visualizarResumoLucro();
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
        // MÉTODO PARA FAZER O TERMINAL DESCANSAR POR ALGUM TEMPO

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            this.naSessao = false;
        }
    }

    private void printItens(ResultSet itens) throws SQLException {
        System.out.println(
            itens.getString("descricao").toUpperCase() + ": " +
            "R$" + itens.getString("preco_venda") + " " +
            "(Q: " + itens.getString("quantidade_comprada") + ")"
        );
    }

    private void mostrarItensNoEstoque(ResultSet itens) throws SQLException {
        while (itens != null && itens.next()) {
            this.printItens(itens);
        }
    }

    private void mostrarItensNoEstoque(ResultSet itens, boolean mostrarCodigo) throws SQLException {
        while (itens != null && itens.next()) {
            if (mostrarCodigo) {
                System.out.print("[ " + itens.getInt("codigo_produto") + " ] ");
            }
            this.printItens(itens);
        }
    }

    private void comprarProduto() throws SQLException {
        // MÉTODO QUE PERMITE A COMPRA DE UM PRODUTO

        ResultSet itens = this.estoque.retornarEstoquePorQuantidade();
        this.mostrarItensNoEstoque(itens, true);

        System.out.print("\nDigite o código do produto (0 para cancelar): ");
        int itemEscolhido = this.inputUsuario.nextInt();

        if(itemEscolhido == 0) {
            return;
        }
        this.dormirAlgumTempo();
        List<Integer> codigosItens = this.estoque.retornarListaCodigoProdutos();
        if (!codigosItens.contains(itemEscolhido)) {
            throw new IllegalArgumentException("\nItem não encontrado no estoque!");
        }
        System.out.print("Quantos você deseja comprar: ");
        int quantidadeEscolhida = this.inputUsuario.nextInt();

        this.dormirAlgumTempo();
        if(
            quantidadeEscolhida > this.estoque.retornarQuantidadeProduto(itemEscolhido) ||
            quantidadeEscolhida <= 0
        ) {
            throw new IllegalArgumentException("\nQuantidade inválida!");
        }
        this.estoque.comprarItemNoEstoque(itemEscolhido, quantidadeEscolhida);

        this.dormirAlgumTempo();
        System.out.println("\nItem(ns) vendidos!");
    }

    // private void adicionarItemAoEstoque() {
    //     // PERMITE ADICIONAR UM NOVO ITEM OU JÁ EXISTENTE NO MAPA 

    //     try {
    //         System.out.print("Código: ");
    //         int codigoProduto = this.inputUsuario.nextInt();

    //         System.out.print("Descrição: ");
    //         String descricaoProduto = this.inputUsuario.nextLine();

    //         System.out.print("Por quanto foi comprado: ");
    //         double precoCompraProduto = this.inputUsuario.nextDouble();

    //         System.out.print("Por quanto será revendido: ");
    //         double precoVendaProduto = this.inputUsuario.nextDouble();

    //         System.out.print("Quantidade à adicionar: ");
    //         int quantidadeProduto = this.inputUsuario.nextInt();

    //         if(quantidadeProduto == 1) {
    //             this.estoque.adicionarItem(
    //                 nomeProduto, 
    //                 descricaoProduto,
    //                 precoCompraProduto, 
    //                 precoVendaProduto
    //             );
    //         } else {
    //             this.estoque.adicionarItem(
    //                 nomeProduto, 
    //                 descricaoProduto,
    //                 precoCompraProduto, 
    //                 precoVendaProduto,
    //                 quantidadeProduto
    //             );
    //         }

            // } else {
            //     System.out.print("Quantidade à adicionar: ");
            //     int quantidadeProduto = this.inputUsuario.nextInt();

            //     this.estoque.adicionarItem(nomeProduto, quantidadeProduto);
            // }

    //         System.out.println("\nItem(ns) adicionado(s) com sucesso!\n");
            
    //     } catch (IllegalArgumentException e) {
    //         this.dormirAlgumTempo();
    //         System.out.println("\nNão foi possível adicionar o(s) item(ns) no estoque");

    //         if(e.getMessage() != "") {
    //             System.out.println(e.getMessage());
    //         }
    //     } catch(InputMismatchException e) {
    //         this.dormirAlgumTempo();
    //         System.out.println("\nNão foi possível adicionar o(s) item(ns) no estoque");

    //         this.inputUsuario.next();
    //     }
    // }

    // private void darBaixaEmItens() {
    //     // IMPRIME OS ITENS BAIXADOS E PERGUNTA SE A BAIXA DOS ITENS É DESEJADA
    //     // CASO SIM, O MÉTODO baixarItens DE Estoque É CHAMADO

    //     if(this.estoque.getItensVendidos().size() == 0) {
    //         System.out.println("Não há itens a serem baixados...");
    //         return;
    //     }
        
    //     System.out.println("Itens vendidos no total:\n");

    //     for(String itemVendido : this.estoque.getItensVendidos().keySet()) {
    //         System.out.println(
    //             itemVendido + ": " + 
    //             this.estoque.getItensVendidos().get(itemVendido).size() + " x " + 
    //             this.estoque.getItensVendidos().get(itemVendido).get(0).getPrecoVenda()
    //         );
    //     }
    //     System.out.print("\nDeseja dar baixa? (S/N): ");
    //     String decisao = this.inputUsuario.nextLine().toUpperCase();
    //     this.dormirAlgumTempo();

    //     if(decisao.equals("S")) {
    //         this.estoque.baixarItens();
    //         System.out.println("Item(ns) baixados com sucesso!");
            
    //     } else if(decisao.equals("N")) {
    //         return;
    //     } else {
    //         throw new IllegalArgumentException("Não é uma opção!");
    //     }
    // }

    // private void visualizarResumoLucro() {
    //     // CHAMA O MÉTODO retornarLucroResumo DE Estoque E IMPRIME O LUCRO

    //     try {
    //         double totalLucro = this.estoque.retornarLucroResumo();
    //         System.out.println("Total vendido: R$" + totalLucro);

    //     } catch(NullPointerException e) {
    //         System.out.println(e.getMessage());
    //     }
    // }

}
