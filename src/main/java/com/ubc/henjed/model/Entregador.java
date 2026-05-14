package com.ubc.henjed.model;

import com.ubc.henjed.Model;
import com.ubc.henjed.util.CMD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Entregador extends Model<Entregador> {

    protected String nome;
    protected String cpf;
    protected String telefone;
    protected char status;

    public ArrayList<Veiculo> getAllVeiculos(Connection conn) throws SQLException, Exception {
        return new Veiculo().getCollection(conn, "WHERE codigo_entregador = ?", this.getCodigo());
    }

    @Override
    public String getTablename() {
        return "Entregador";
    }

    @Override
    protected String getOrder() {
        return "(nome,cpf,telefone,status)";
    }

    @Override
    public void cadastroCMD(Connection conn) throws SQLException, Exception {
        setNome(CMD.promptLine("Digite seu nome", 60));
        setCpf(CMD.promptLine("Digite seu CPF", 11));
        setTelefone(CMD.promptLine("Digite seu telefone", 15));
        setStatus('D');

        super.cadastroCMD(conn);
    }

    public Entregador() {
        super();
    }

    public Entregador(String nome, String cpf, String telefone, char status) {
        super();
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.status = status;
    }

    @Override
    protected void setValues(ResultSet rs) throws SQLException {
        this.nome = rs.getString("nome");
        this.cpf = rs.getString("cpf");
        this.telefone = rs.getString("telefone");
        this.status = rs.getString("status").charAt(0);
    }

    @Override
    protected void insertValues(PreparedStatement st, int currentOrder)
        throws SQLException {
        super.insertValues(st, currentOrder);
        st.setString(getCounter(), this.nome);
        st.setString(getCounter(), this.cpf);
        st.setString(getCounter(), this.telefone);
        st.setString(getCounter(), "" + this.status);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return String.format("Nome: %s | Status: %s | Telefone: %s | CPF: %s", this.getNome(),this.getStatus(),this.getTelefone(),this.getCpf());
    }
}
