# CANTINA DO IFAL v2.0

Nova versão do projeto solicitado pelos professores Ricardo Nunes, coordenador do curso de Desenvolvimento de Sistemas e professor de Programação Orientada a Objetos, e Wladia Bessa, professora de Banco de Dados, para turma 922A do Instituto Federal de Alagoas (IFAL) no dia 30/05/2022.
O trabalho consiste em produzir uma simulação de cantina, onde um estoque tem que ser administrado, podendo adicionar itens, dar baixa neles, e visualizar os itens dependendo da ordem que seja escolhida com todo o sistema integrado com MySQL com a biblioteca JDBC e com a implementação de interface gráfica (GUI) usando a biblioteca Swing do Java.

Foi usada a linguagem de programação Java para a produção do projeto, com o objetivo de seguir o paradigma de Orientação a Objetos.

## Integrantes

- Alexia Rodrigues Oliveira;
- Bruno Lucas dos Santos;
- Isaque Braga;
- Maria Graziella Vilela;
- Ytalo Amorim;

## Requisitos

Nem todos os requisitos foram cumpridos nessa versão do projeto, faltando a adaptação das funcionalidades de visualizar resumos de lucro para a interface gráfica, além da possibilidade de baixar os itens vendidos. A falta de outras funcionalidades foi percebida pela professora durante a apresentação, como o método de excluir produtos do estoque, editar/incrementar, etc.

Outro ponto é que, graças à falta de algumas funcionalidades, nem todas as tabelas criadas no MySQL são utilizadas ou corretamente utilizadas.

## Dificuldades

A maior dificuldade na produção desse projeto foi o curto tempo disponível. Já dentro do código, houve dificuldade da equipe de trabalhar com eventos em uma interface gráfica. 

## Links utilizados

- <https://stackoverflow.com/a/1498029>;
- <https://www.geeksforgeeks.org/different-ways-reading-text-file-java/>;

## Como utilizar

Para utilizar do programa apenas é preciso ter o XAMPP Apache (ou alguma conexão com MySQL) aberta antes de rodá-lo. Todo o resto (criação de tabela, conexão) é feito pelo próprio programa, e o povoamento das tabelas é feito pela própria interface gráfica.

**Para caso de acesso proibido no XAMPP no momento de acessar ao banco de dados, basta verificar a porta sendo usada na classe ConnectionFactory()**

#### Finalizado em 13/06/22
