-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 11/06/2022 às 18:38
-- Versão do servidor: 10.4.24-MariaDB
-- Versão do PHP: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

DROP DATABASE IF EXISTS `cantinaifal`;
CREATE DATABASE `cantinaifal`;

--
-- Banco de dados: `cantinaifal`
--

-- --------------------------------------------------------

--
-- Estrutura para tabela `cadastra`
--

DROP TABLE IF EXISTS cantinaifal.`cadastra`;
CREATE TABLE cantinaifal.`cadastra` (
  `codigo_login` int(5) NOT NULL,
  `codigo_produto` int(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estrutura para tabela `funcionario`
--

DROP TABLE IF EXISTS cantinaifal.`funcionario`;
CREATE TABLE cantinaifal.`funcionario` (
  `codigo_login` int(5) NOT NULL,
  `nome` varchar(255) NOT NULL,
  `senha` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estrutura para tabela `itemvenda`
--

DROP TABLE IF EXISTS cantinaifal.`itemvenda`;
CREATE TABLE cantinaifal.`itemvenda` (
  `id_itemvenda` int(5) NOT NULL,
  `codigo_produto` int(5) NOT NULL,
  `preço` decimal(4,2) NOT NULL,
  `quantidade` int(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estrutura para tabela `produto`
--

DROP TABLE IF EXISTS cantinaifal.`produto`;
CREATE TABLE cantinaifal.`produto` (
  `codigo_produto` int(5) NOT NULL,
  `descricao` varchar(255) NOT NULL,
  `preco_compra` decimal(4,2) NOT NULL,
  `preco_venda` decimal(4,2) NOT NULL,
  `quantidade_comprada` int(5) NOT NULL,
  `estoque_minimo` int(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estrutura para tabela `venda`
--

DROP TABLE IF EXISTS cantinaifal.`venda`;
CREATE TABLE cantinaifal.`venda` (
  `codigo_venda` int(5) NOT NULL,
  `data_venda` date NOT NULL,
  `desconto` decimal(4,2) NOT NULL,
  `total_venda` decimal(4,2) NOT NULL,
  `forma_pagamento` varchar(255) NOT NULL,
  `codigo_funcionario` int(5) NOT NULL,
  `id_itemvenda` int(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Índices para tabelas despejadas
--

--
-- Índices de tabela `cadastra`
--
ALTER TABLE cantinaifal.`cadastra`
  ADD PRIMARY KEY (`codigo_login`,`codigo_produto`),
  ADD KEY `codigo_produto` (`codigo_produto`);

--
-- Índices de tabela `funcionario`
--
ALTER TABLE cantinaifal.`funcionario`
  ADD PRIMARY KEY (`codigo_login`);

--
-- Índices de tabela `itemvenda`
--
ALTER TABLE cantinaifal.`itemvenda`
  ADD PRIMARY KEY (`id_itemvenda`,`codigo_produto`),
  ADD KEY `codigo_produto` (`codigo_produto`);

--
-- Índices de tabela `produto`
--
ALTER TABLE cantinaifal.`produto`
  ADD PRIMARY KEY (`codigo_produto`);

--
-- Índices de tabela `venda`
--
ALTER TABLE cantinaifal.`venda`
  ADD PRIMARY KEY (`codigo_venda`,`codigo_funcionario`,`id_itemvenda`),
  ADD KEY `codigo_funcionario` (`codigo_funcionario`),
  ADD KEY `id_itemvenda` (`id_itemvenda`);

--
-- Restrições para tabelas despejadas
--

--
-- Restrições para tabelas `cadastra`
--
ALTER TABLE cantinaifal.`cadastra`
  ADD CONSTRAINT `cadastra_ibfk_1` FOREIGN KEY (`codigo_login`) REFERENCES `funcionario` (`codigo_login`),
  ADD CONSTRAINT `cadastra_ibfk_2` FOREIGN KEY (`codigo_produto`) REFERENCES `produto` (`codigo_produto`);

--
-- Restrições para tabelas `itemvenda`
--
ALTER TABLE cantinaifal.`itemvenda`
  ADD CONSTRAINT `itemvenda_ibfk_1` FOREIGN KEY (`codigo_produto`) REFERENCES `produto` (`codigo_produto`);

--
-- Restrições para tabelas `venda`
--
ALTER TABLE cantinaifal.`venda`
  ADD CONSTRAINT `venda_ibfk_1` FOREIGN KEY (`codigo_funcionario`) REFERENCES `funcionario` (`codigo_login`),
  ADD CONSTRAINT `venda_ibfk_2` FOREIGN KEY (`id_itemvenda`) REFERENCES `itemvenda` (`id_itemvenda`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
