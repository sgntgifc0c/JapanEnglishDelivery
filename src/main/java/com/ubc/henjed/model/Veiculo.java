package com.ubc.henjed.model;

import com.ubc.henjed.Model;
import com.ubc.henjed.util.CMD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Veiculo extends Model<Veiculo> {

    protected String placa = "";
    protected String tipoVeiculo;
    protected String marca;
    protected int codigoEntregador;

    public Entregador getEntregador(Connection conn) throws SQLException {
        var entregador = new Entregador();
        entregador.getByCodigo(conn, this.codigoEntregador);
        return entregador;
    }

    @Override
    public String getTablename() {
        return "Veiculo";
    }

    @Override
    protected String getOrder() {
        return "(placa,tipo_veiculo,marca,codigo_entregador)";
    }

    @Override
    public void cadastroCMD(Connection conn) throws SQLException {
        setTipoVeiculo(
            CMD.promptLine(
                "Selecione tipo do veiculo (B = Bicicleta, C = Carro, M = Moto)",
                1
            )
        );
        setMarca(CMD.promptLine("Escreva a marca e/ou modelo do veiculo", 70));
        setPlaca(
            CMD.promptLine(
                "Escreva a placa do veiculo (Não é necessario se for Bicicleta)",
                7
            )
        );
        if (getPlaca().length() == 0 && getTipoVeiculo() != "B") {
            CMD.msg(
                "Placa não pode ser vazia se o veiculo não for uma bicicleta, cancelando cadastro..."
            );
            return;
        }
        super.cadastroCMD(conn);
    }

    // esse overload existe para facilitar o cadastramento de veiculos dentro da interface do entregador
    public void cadastroCMD(Connection conn, int codigoEntregador)
        throws SQLException {
        this.codigoEntregador = codigoEntregador;
        this.cadastroCMD(conn);
    }

    public Veiculo() {
        super();
    }

    public Veiculo(
        String placa,
        String tipoVeiculo,
        String marca,
        int codigoEntregador
    ) {
        super();
        this.placa = placa;
        this.tipoVeiculo = tipoVeiculo;
        this.marca = marca;
        this.codigoEntregador = codigoEntregador;
    }

    @Override
    protected void setValues(ResultSet rs) throws SQLException {
        this.placa = rs.getString("placa");
        this.tipoVeiculo = rs.getString("tipoVeiculo");
        this.marca = rs.getString("marca");
        this.codigoEntregador = rs.getInt("codigoEntregador");
    }

    @Override
    protected void insertValues(PreparedStatement st, int currentOrder)
        throws SQLException {
        super.insertValues(st, currentOrder);
        st.setString(getCounter(), this.placa);
        st.setString(getCounter(), this.tipoVeiculo);
        st.setString(getCounter(), this.marca);
        st.setInt(getCounter(), this.codigoEntregador);
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getTipoVeiculo() {
        return tipoVeiculo;
    }

    public void setTipoVeiculo(String tipoVeiculo) {
        this.tipoVeiculo = tipoVeiculo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getCodigoEntregador() {
        return codigoEntregador;
    }

    public void setCodigoEntregador(int codigoEntregador) {
        this.codigoEntregador = codigoEntregador;
    }
}
