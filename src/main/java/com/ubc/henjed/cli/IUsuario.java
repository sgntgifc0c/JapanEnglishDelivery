package com.ubc.henjed.cli;

import java.sql.SQLException;

public interface IUsuario {
    // Demonstra o menu de selecao para o usuario
    void exibirMenu();
    // Verifica o digito do usuario para uma das opções do menu
    // Retorna true se o usuario pediu para fechar o menu
    boolean selecao() throws SQLException, Exception;
    // Uma interface de usuario para cadastrar um novo modelo para o Banco de dados
    // Após as mudanças a classe guarda o modelo cadastrado no login.
    void cadastro() throws SQLException;
}
