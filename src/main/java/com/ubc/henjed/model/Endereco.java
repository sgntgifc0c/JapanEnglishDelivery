package com.ubc.henjed.model;

import com.ubc.henjed.Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Endereco extends Model<Endereco> {

    protected String rua;
    protected String numero;
    protected String bairro;
    protected String cidade;
	protected String estado;
	protected String cep;

	@Override
	public String getTablename() {
		return "Produto";
	}

	@Override
	protected String getOrder() {
		return "(rua,numero,bairro,cidade,estado,cep)";
	}

	public Endereco() {
		super();
	}

	public Endereco(String rua, String numero, String bairro, String cidade, String estado, String cep) {
		super();
		this.rua = rua;
		this.numero = numero;
		this.bairro = bairro;
		this.cidade = cidade;
		this.estado = estado;
		this.cep = cep;
	}

	@Override
    protected void setValues(ResultSet rs) throws SQLException {
        this.rua = rs.getString("rua");
        this.numero = rs.getString("numero");
        this.bairro = rs.getString("bairro");
        this.cidade = rs.getString("cidade");
		this.estado = rs.getString("estado");
		this.cep = rs.getString("cep");
    }

	@Override
	protected void insertValues(PreparedStatement st, int currentOrder) throws SQLException {
		super.insertValues(st, currentOrder);
		st.setString(getCounter(), this.rua);
		st.setString(getCounter(), this.numero);
		st.setString(getCounter(), this.bairro);
		st.setString(getCounter(), this.cidade);
		st.setString(getCounter(), this.estado);
		st.setString(getCounter(), this.cep);
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}
}
