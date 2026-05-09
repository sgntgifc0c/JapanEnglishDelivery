/* =========================================================
   PROJETO: Delivery

   GRUPO: Henrique De Aguiar Fernandes, Gabriel Romão da Silva
   Geziel de Andrade, Luiz Eduardo dos Reis, Pedro Viana da Silva

   DISCIPLINA: Banco de Dados
   PROFESSOR: Caio Gustavo Rodrigues da Cruz
   DATA: 01/05/2026
 
   DESCRIÇÃO:
   Criação do banco de dados
 
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
 

CREATE DATABASE delivery OWNER postgres; -- cria base de dados
\c delivery postgres
-- conecta na base de dados
-- a partir desse ponto o script que cria as tabelas é executado

 
/* =========================================================
OBSERVAÇÕES
========================================================= */
 
-- Qualquer informação adicional relevante
-- Ex: dependências entre scripts, ordem de execução, etc.
 
 
