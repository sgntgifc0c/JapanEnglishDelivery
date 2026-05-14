package com.ubc.henjed.model;

import com.ubc.henjed.Model;
import com.ubc.henjed.util.CMD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

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

    public double getValorTotal(Connection conn)
        throws SQLException, Exception {
        var list = getAllItems(conn);
        double result = 0;

        for (ItemPedido item : list) {
            var produto = item.getProduto(conn);
            result += produto.preco * item.quantidade;
        }

        return result;
    }

    public ArrayList<ItemPedido> getAllItems(Connection conn)
        throws SQLException, Exception {
        return new ItemPedido().getCollection(
            conn,
            "WHERE codigo_pedido = ?",
            this.getCodigo()
        );
    }

    @Override
    public String getTablename() {
        return "Pedido";
    }

    @Override
    protected String getOrder() {
        return "(codigo_cliente,codigo_restaurante,codigo_entregador,status)";
    }

    @Override
    public void cadastroCMD(Connection conn) throws SQLException, Exception {
        var rest = Integer.valueOf(
            CMD.promptLine("Digite o ID do restaurante:", 3)
        );

        var re = new Restaurante();
        re.getByCodigo(conn, rest);

        if (!re.doesItExist()) {
            CMD.msg("Restaurante não encontrado, voltando...");
            return;
        }

        setCodigoRestaurante(rest);
        setStatus('P');

        super.cadastroCMD(conn);
    }

    public void cadastroCMD(Connection conn, int codigoCliente)
        throws SQLException, Exception {
        setCodigoCliente(codigoCliente);
        this.cadastroCMD(conn);
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
        if (this.codigoEntregador <= 0) {
            st.setNull(getCounter(), Types.INTEGER);
        } else {
            st.setInt(getCounter(), this.codigoEntregador);
        }
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

    @Override
    public String toString() {
        return String.format(
            "ID: %d | Status: %c",
            this.getCodigo(),
            this.getStatus()
        );
    }
}
