/* =========================================================
   PROJETO: Delivery

   GRUPO: Henrique De Aguiar Fernandes, Gabriel Romão da Silva
   Geziel de Andrade, Luiz Eduardo dos Reis, Pedro Viana da Silva

   DISCIPLINA: Banco de Dados
   PROFESSOR: Caio Gustavo Rodrigues da Cruz
   DATA: 01/05/2026

   DESCRIÇÃO:
   Criação das tabelas para o banco de dados

   REFERÊNCIA:
   Script baseado no dicionário de dados versão <X.X>
   ========================================================= */


/* =========================================================
   CONFIGURAÇÕES INICIAIS
   ========================================================= */

-- Define schema (se aplicável)
-- SET search_path TO public;


/* =========================================================
   CRIAÇÃO DE OBJETOS
   ========================================================= */


CREATE TABLE Endereco (
   codigo SERIAL PRIMARY KEY,
   rua varchar(100) NOT NULL,
   numero varchar(4) NOT NULL,
   bairro varchar(33) NOT NULL,
   cidade varchar(33) NOT NULL,
   estado varchar(2) NOT NULL, --sigla
   cep varchar(8) NOT NULL
);

CREATE TABLE Cliente (
   codigo SERIAL PRIMARY KEY,
   nome varchar(60) NOT NULL,
   cpf varchar(11) NOT NULL,
   email varchar(254) NOT NULL,
   telefone varchar(15),
   codigo_endereco int NOT NULL,
   CONSTRAINT fk_endereco
      FOREIGN KEY (codigo_endereco)
      REFERENCES Endereco (codigo)
);

CREATE TABLE Entregador (
   codigo SERIAL PRIMARY KEY,
   nome varchar(60) NOT NULL,
   cpf varchar(11) NOT NULL,
   telefone varchar(15) NOT NULL, -- em minha opinião, acho importante que pelo menos o entregador tenha telefone
   status varchar(1) NOT NULL DEFAULT 'D' -- para motivos de otimização, o status ira ter apenas um caractere, o programa Java ira entender esse valor
);

CREATE TABLE Veiculo (
   codigo SERIAL PRIMARY KEY,
   placa varchar(7), -- assumindo que siga o padrão das placas do mercosul
   tipo_veiculo varchar(1) NOT NULL, -- denovo um caractere, por motivos de otimização
   marca varchar(70) NOT NULL,
   codigo_entregador int NOT NULL,
   CONSTRAINT fk_entregador
      FOREIGN KEY (codigo_entregador)
      REFERENCES Entregador (codigo)
);

CREATE TABLE Restaurante (
   codigo SERIAL PRIMARY KEY,
   nome varchar(75) NOT NULL,
   cnpj varchar(14) NOT NULL,
   telefone varchar(15) NOT NULL,
   categoria varchar(15),
   codigo_endereco int NOT NULL,
   CONSTRAINT fk_endereco
      FOREIGN KEY (codigo_endereco)
      REFERENCES Endereco (codigo)
);

CREATE TABLE Produto (
   codigo SERIAL PRIMARY KEY,
   nome varchar(75) NOT NULL,
   descricao varchar(100),
   preco numeric(8,2) NOT NULL CHECK (preco > 0),
   categoria varchar(15),
   codigo_restaurante int NOT NULL,
   CONSTRAINT fk_restaurante
      FOREIGN KEY (codigo_restaurante)
      REFERENCES Restaurante (codigo)
);

CREATE TABLE Pedido (
   codigo SERIAL PRIMARY KEY,
   codigo_cliente int NOT NULL,
   codigo_restaurante int NOT NULL,
   codigo_entregador int,
   status varchar(1) NOT NULL DEFAULT 'P',
   CONSTRAINT fk_cliente
      FOREIGN KEY (codigo_cliente)
      REFERENCES Cliente (codigo),
   CONSTRAINT fk_restaurante
      FOREIGN KEY (codigo_restaurante)
      REFERENCES Restaurante (codigo),
   CONSTRAINT fk_entregador
      FOREIGN KEY (codigo_entregador)
      REFERENCES Entregador (codigo)
);

CREATE TABLE ItemPedido (
   codigo SERIAL PRIMARY KEY,
   codigo_pedido int NOT NULL,
   codigo_produto int NOT NULL,
   quantidade int NOT NULL CHECK (quantidade > 0) DEFAULT 1,
   CONSTRAINT fk_pedido
      FOREIGN KEY (codigo_pedido)
      REFERENCES Pedido (codigo),
   CONSTRAINT fk_produto
      FOREIGN KEY (codigo_produto)
      REFERENCES Produto (codigo)
);

/* =========================================================
OBSERVAÇÕES
========================================================= */

-- Qualquer informação adicional relevante
-- Ex: dependências entre scripts, ordem de execução, etc.

-- Quantidade maxima de caracteres de email:
-- https://stackoverflow.com/questions/386294/what-is-the-maximum-length-of-a-valid-email-address#574698
