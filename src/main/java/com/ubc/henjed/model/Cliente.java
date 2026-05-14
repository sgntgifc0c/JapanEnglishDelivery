package com.ubc.henjed.model;

import com.ubc.henjed.Model;
import com.ubc.henjed.util.CMD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Cliente extends Model<Cliente> {

    protected String nome;
    protected String cpf;
    protected String email;
    protected int codigoEndereco;
    protected String telefone = "";

    public Endereco getEndereco(Connection conn) throws SQLException {
        var end = new Endereco();
        end.getByCodigo(conn, codigoEndereco);
        return end;
    }

    @Override
    public String getTablename() {
        return "Cliente";
    }

    @Override
    protected String getOrder() {
        return "(nome,cpf,email,telefone,codigo_endereco)";
    }

    @Override
    public void cadastroCMD(Connection conn) throws SQLException, Exception {
        setNome(CMD.promptLine("Digite o seu nome", 60));
        setCpf(CMD.promptLine("Digite seu CPF (apenas numeros)", 11));
        setEmail(CMD.promptLine("Digite seu Email", 254));
        setTelefone(
            CMD.promptLine("Digite seu telefone (não obrigatório)", 15, true)
        );

        CMD.msg("Agora digite seu endereço:");

        var endereco = (this.codigoEndereco >= 0)
            ? this.getEndereco(conn)
            : new Endereco();
        endereco.cadastroCMD(conn);
        setCodigoEndereco(endereco.getCodigo());

        super.cadastroCMD(conn);
    }

    @Override
    protected void setValues(ResultSet rs) throws SQLException {
        this.nome = rs.getString("nome");
        this.cpf = rs.getString("cpf");
        this.email = rs.getString("email");
        this.telefone = rs.getString("telefone");
        this.codigoEndereco = rs.getInt("codigo_endereco");
    }

    @Override
    protected void insertValues(PreparedStatement st, int currentOrder)
        throws SQLException {
        super.insertValues(st, currentOrder);
        st.setString(getCounter(), this.nome);
        st.setString(getCounter(), this.cpf);
        st.setString(getCounter(), this.email);
        st.setString(getCounter(), this.telefone);
        st.setInt(getCounter(), this.codigoEndereco);
    }

    public Cliente() {
        super();
    }

    public Cliente(
        String nome,
        String cpf,
        String email,
        String telefone,
        int codigoEndereco
    ) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
        this.codigoEndereco = codigoEndereco;
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

    public int getCodigoEndereco() {
        return codigoEndereco;
    }

    public void setCodigoEndereco(int codigoEndereco) {
        this.codigoEndereco = codigoEndereco;
    }

    @Override
    public String toString() {
        return String.format(
            "Nome: %s | CPF: %s | Telefone: %s | Email: %s",
            this.getNome(),
            this.getCpf(),
            this.getTelefone(),
            this.getEmail()
        );
    }
}
