package com.ubc.henjed.model;

import com.ubc.henjed.Model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Restaurante extends Model<Restaurante> {

    protected String nome;
    protected String cnpj;
    protected String telefone;
    protected String categoria = "";
    protected int codigoEndereco;

    public Endereco getEndereco(Connection conn) throws SQLException {
        var end = new Endereco();
        end.getByCodigo(conn, codigoEndereco);
        return end;
    }

    @Override
    public String getTablename() {
        return "Restaurante";
    }

    @Override
    protected String getOrder() {
        return "(nome,cnpj,telefone,categoria,codigo_endereco)";
    }

    public Restaurante() {
        super();
    }

    public Restaurante(
        String nome,
        String cnpj,
        String telefone,
        String categoria,
        int codigoEndereco
    ) {
        super();
        this.nome = nome;
        this.cnpj = cnpj;
        this.telefone = telefone;
        this.categoria = categoria;
        this.codigoEndereco = codigoEndereco;
    }

    @Override
    protected void setValues(ResultSet rs) throws SQLException {
        this.nome = rs.getString("nome");
        this.cnpj = rs.getString("cnpj");
        this.telefone = rs.getString("telefone");
        this.categoria = rs.getString("categoria");
        this.codigoEndereco = rs.getInt("codigo_endereco");
    }

    @Override
    protected void insertValues(PreparedStatement st, int currentOrder)
        throws SQLException {
        super.insertValues(st, currentOrder);
        st.setString(getCounter(), this.nome);
        st.setString(getCounter(), this.cnpj);
        st.setString(getCounter(), this.telefone);
        st.setString(getCounter(), this.categoria);
        st.setInt(getCounter(), this.codigoEndereco);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getCodigoEndereco() {
        return codigoEndereco;
    }

    public void setCodigoEndereco(int codigoEndereco) {
        this.codigoEndereco = codigoEndereco;
    }
}
