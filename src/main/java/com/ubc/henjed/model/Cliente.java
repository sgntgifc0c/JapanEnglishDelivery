package com.ubc.henjed.model;

import com.ubc.henjed.Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Cliente extends Model<Cliente> {

    protected String nome;
    protected String cpf;
    protected String email;
    protected String telefone = "";

	@Override
	public String getTablename() {
		return "Cliente";
	}

	@Override
	protected String getOrder() {
		return "(nome,cpf,email,telefone)";
	}

	@Override
    protected void setValues(ResultSet rs) throws SQLException {
        this.nome = rs.getString("nome");
        this.cpf = rs.getString("cpf");
        this.email = rs.getString("email");
        this.telefone = rs.getString("telefone");
    }

	@Override
	protected void insertValues(PreparedStatement st, int currentOrder) throws SQLException {
		super.insertValues(st, currentOrder);
		st.setString(getCounter(), this.nome);
		st.setString(getCounter(), this.cpf);
		st.setString(getCounter(), this.email);
		st.setString(getCounter(), this.telefone);
	}

	public Cliente() {
		super();
	}

	public Cliente(String nome, String cpf, String email, String telefone) {
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		this.telefone = telefone;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
}
