package com.ubc.henjed.model;

import com.ubc.henjed.Model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemPedido extends Model<ItemPedido> {

    protected int codigoPedido;
    protected int codigoProduto;
    protected int quantidade;

    public Pedido getPedido(Connection conn) throws SQLException {
        var ped = new Pedido();
        ped.getByCodigo(conn, codigoPedido);
        return ped;
    }

    public Produto getProduto(Connection conn) throws SQLException {
        var pro = new Produto();
        pro.getByCodigo(conn, codigoProduto);
        return pro;
    }

    @Override
    public String getTablename() {
        return "ItemPedido";
    }

    @Override
    protected String getOrder() {
        return "(codigo_pedido,codigo_produto,quantidade)";
    }

    public ItemPedido() {
        super();
    }

    public ItemPedido(int codigoPedido, int codigoProduto, int quantidade) {
        super();
        this.codigoPedido = codigoPedido;
        this.codigoProduto = codigoProduto;
        this.quantidade = quantidade;
    }

    @Override
    public void setValues(ResultSet rs) throws SQLException {
        this.codigoPedido = rs.getInt("codigo_pedido");
        this.codigoProduto = rs.getInt("codigo_produto");
        this.quantidade = rs.getInt("quantidade");
    }

    @Override
    public void insertValues(PreparedStatement st, int currentOrder)
        throws SQLException {
        super.insertValues(st, currentOrder);
        st.setInt(getCounter(), this.codigoPedido);
        st.setInt(getCounter(), this.codigoProduto);
        st.setInt(getCounter(), this.quantidade);
    }

    public int getCodigoPedido() {
        return codigoPedido;
    }

    public void setCodigoPedido(int codigoPedido) {
        this.codigoPedido = codigoPedido;
    }

    public int getCodigoProduto() {
        return codigoProduto;
    }

    public void setCodigoProduto(int codigoProduto) {
        this.codigoProduto = codigoProduto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
