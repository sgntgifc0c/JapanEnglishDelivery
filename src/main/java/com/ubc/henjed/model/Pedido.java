package com.ubc.henjed.model;

import com.ubc.henjed.Model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Pedido extends Model<Pedido> {

    protected int codigoCliente;
    protected int codigoRestaurante;
    protected int codigoEntregador = 0;
    protected char status;

    public Cliente getCliente(Connection conn) throws SQLException {
        var cli = new Cliente();
        cli.getByCodigo(conn, codigoCliente);
        return cli;
    }

    public Restaurante getRestaurante(Connection conn) throws SQLException {
        var res = new Restaurante();
        res.getByCodigo(conn, codigoRestaurante);
        return res;
    }

    public Entregador getEntregador(Connection conn) throws SQLException {
        var ent = new Entregador();
        ent.getByCodigo(conn, codigoEntregador);
        return ent;
    }

    @Override
    public String getTablename() {
        return "Pedido";
    }

    @Override
    protected String getOrder() {
        return "(codigo_cliente,codigo_restaurante,codigo_entregador,status)";
    }

    public Pedido() {
        super();
    }

    public Pedido(
        int codigoCliente,
        int codigoRestaurante,
        int codigoEntregador,
        char status
    ) {
        super();
        this.codigoCliente = codigoCliente;
        this.codigoRestaurante = codigoRestaurante;
        this.codigoEntregador = codigoEntregador;
        this.status = status;
    }

    @Override
    protected void setValues(ResultSet rs) throws SQLException {
        this.codigoCliente = rs.getInt("codigo_cliente");
        this.codigoRestaurante = rs.getInt("codigo_restaurante");
        this.codigoEntregador = rs.getInt("codigo_entregador");
        this.status = rs.getString("status").charAt(0);
    }

    @Override
    protected void insertValues(PreparedStatement st, int currentOrder)
        throws SQLException {
        super.insertValues(st, currentOrder);
        st.setInt(getCounter(), this.codigoCliente);
        st.setInt(getCounter(), this.codigoRestaurante);
        st.setInt(getCounter(), this.codigoEntregador);
        st.setString(getCounter(), "" + this.status);
    }

    public int getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(int codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public int getCodigoRestaurante() {
        return codigoRestaurante;
    }

    public void setCodigoRestaurante(int codigoRestaurante) {
        this.codigoRestaurante = codigoRestaurante;
    }

    public int getCodigoEntregador() {
        return codigoEntregador;
    }

    public void setCodigoEntregador(int codigoEntregador) {
        this.codigoEntregador = codigoEntregador;
    }

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }
}
