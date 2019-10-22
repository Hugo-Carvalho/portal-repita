-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 22-Out-2019 às 19:50
-- Versão do servidor: 10.4.6-MariaDB
-- versão do PHP: 7.3.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `portalrepita`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `cronograma`
--

CREATE TABLE `cronograma` (
  `id` bigint(20) NOT NULL,
  `nome` varchar(255) NOT NULL,
  `receptor` varchar(255) NOT NULL,
  `robo` varchar(255) DEFAULT NULL,
  `data_inicio` varchar(255) DEFAULT NULL,
  `hora_inicio` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `cronograma_executado`
--

CREATE TABLE `cronograma_executado` (
  `id` bigint(20) NOT NULL,
  `nome` varchar(255) NOT NULL,
  `receptor` varchar(255) NOT NULL,
  `robo` varchar(255) NOT NULL,
  `data_inicio` varchar(255) NOT NULL,
  `hora_inicio` varchar(255) NOT NULL,
  `data_termino` varchar(255) NOT NULL,
  `hora_termino` varchar(255) NOT NULL,
  `status` varchar(255) NOT NULL,
  `mensagem` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `cronograma_executando`
--

CREATE TABLE `cronograma_executando` (
  `id` bigint(20) NOT NULL,
  `nome` varchar(255) NOT NULL,
  `receptor` varchar(255) NOT NULL,
  `robo` varchar(255) NOT NULL,
  `data_inicio` varchar(255) NOT NULL,
  `hora_inicio` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `usuario`
--

CREATE TABLE `usuario` (
  `id` bigint(20) NOT NULL,
  `login` varchar(255) NOT NULL,
  `senha` varchar(255) NOT NULL,
  `nome` varchar(255) NOT NULL,
  `tipo` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `usuario`
--

INSERT INTO `usuario` (`id`, `login`, `senha`, `nome`, `tipo`) VALUES
(1, 'admin', 'admin', 'Administrador', 'admin');

--
-- Índices para tabelas despejadas
--

--
-- Índices para tabela `cronograma`
--
ALTER TABLE `cronograma`
  ADD PRIMARY KEY (`id`);

--
-- Índices para tabela `cronograma_executado`
--
ALTER TABLE `cronograma_executado`
  ADD PRIMARY KEY (`id`);

--
-- Índices para tabela `cronograma_executando`
--
ALTER TABLE `cronograma_executando`
  ADD PRIMARY KEY (`id`);

--
-- Índices para tabela `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `cronograma`
--
ALTER TABLE `cronograma`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de tabela `cronograma_executado`
--
ALTER TABLE `cronograma_executado`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de tabela `cronograma_executando`
--
ALTER TABLE `cronograma_executando`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT de tabela `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
