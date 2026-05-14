package com.ubc.henjed.model;

import com.ubc.henjed.Model;
import com.ubc.henjed.util.CMD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Produto extends Model<Produto> {

    protected String nome;
    protected String descricao = "";
    protected double preco;
    protected String categoria;
    protected int codigoRestaurante;

    public Restaurante getRestaurante(Connection conn) throws SQLException {
        var res = new Restaurante();
        res.getByCodigo(conn, codigoRestaurante);
        return res;
    }

    @Override
    public String getTablename() {
        return "Produto";
    }

    @Override
    protected String getOrder() {
        return "(nome,descricao,preco,categoria,codigo_restaurante)";
    }

    @Override
    public void cadastroCMD(Connection conn) throws SQLException, Exception {
        setNome(CMD.promptLine("Digite o nome do Produto", 75));
        setDescricao(
            CMD.promptLine("Digite a descricao do Produto", 100, true)
        );
        setPreco(
            Double.valueOf(
                CMD.promptLine(
                    "Digite o preco do produto (Formato: 00000.00)",
                    8
                )
            )
        );
        setCategoria(CMD.promptLine("Digite a categoria do Produto", 15));

        super.cadastroCMD(conn);
    }

    public void cadastroCMD(Connection conn, int codigoRestaurante)
        throws SQLException, Exception {
        this.codigoRestaurante = codigoRestaurante;
        this.cadastroCMD(conn);
    }

    public Produto() {
        super();
    }

    public Produto(
        String nome,
        String descricao,
        double preco,
        String categoria,
        int codigoRestaurante
    ) {
        super();
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.categoria = categoria;
        this.codigoRestaurante = codigoRestaurante;
    }

    @Override
    protected void setValues(ResultSet rs) throws SQLException {
        this.nome = rs.getString("nome");
        this.descricao = rs.getString("descricao");
        this.preco = rs.getDouble("preco");
        this.categoria = rs.getString("categoria");
        this.codigoRestaurante = rs.getInt("codigo_restaurante");
    }

    @Override
    protected void insertValues(PreparedStatement st, int currentOrder)
        throws SQLException {
        super.insertValues(st, currentOrder);
        st.setString(getCounter(), this.nome);
        st.setString(getCounter(), this.descricao);
        st.setDouble(getCounter(), this.preco);
        st.setString(getCounter(), this.categoria);
        st.setInt(getCounter(), this.codigoRestaurante);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getCodigoRestaurante() {
        return codigoRestaurante;
    }

    public void setCodigoRestaurante(int codigoRestaurante) {
        this.codigoRestaurante = codigoRestaurante;
    }

    @Override
    public String toString() {
        return String.format(
            "Nome: %s | Descrição: %s | Preco: %f | Categoria: %s | ID: %d",
            this.getNome(),
            this.getDescricao(),
            this.getPreco(),
            this.getCategoria(),
            this.getCodigo()
        );
    }
}
