package com.ubc.henjed.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ubc.henjed.Model;

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
		return "Produto";
	}

	@Override
	protected String getOrder() {
		return "(placa,tipo_veiculo,marca,codigo_entregador)";
	}

	public Veiculo() {
        super();
	}

	public Veiculo(String placa, String tipoVeiculo, String marca, int codigoEntregador) {
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
    protected void insertValues(PreparedStatement st, int currentOrder) throws SQLException {
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
