package com.ubc.henjed;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;

public abstract class Model<T extends Model<T>> {
    // todas as tabelas tem "codigo" como PRIMARY KEY
    private int codigo;
    // para verificar se essa instancia existe no banco de dados
    private boolean existsInDB;

    public int getCodigo() {
		return codigo;
	}
	public boolean doesItExist() {
		return existsInDB;
	}

	// contador
    private int counter = 0;


    abstract public String getTablename();

    abstract protected String getOrder();

    // Cada classe baseada no Model deve ter esse metodo para aplicar as variaveis de cada coluna
    abstract protected void setValues(ResultSet rs) throws SQLException;

    protected void insertValues(PreparedStatement st, int currentOrder) throws SQLException {
        resetCounterTo(currentOrder);
    };

    public Model() {
        this.existsInDB = false;
    }

    public void getByCodigo(Connection conn, int codigo) throws SQLException {
        var sqlq = String.format("SELECT * FROM %s WHERE codigo = ?;", getTablename());

        var st = conn.prepareStatement(sqlq);

        st.setInt(1, codigo);

        var rs = st.executeQuery();

        if (rs.next()) {
            this.codigo = rs.getInt("codigo");
            setValues(rs);
            existsInDB = true;
        }

        rs.close();
        st.close();
    }

    // Esse código usa generics para evitar repetição.
    public ArrayList<T> getCollection(Connection conn, String additionalQuery, Object... questionMarkValues) throws Exception {

        try {
            var result_list = new ArrayList<T>();

            var sqlq = String.format("SELECT * FROM %s %s", getTablename(), additionalQuery);
            var targetModel = this.getClass().getCanonicalName();

            var currentModel = Class.forName(targetModel);

            var constructorModel = currentModel.getConstructor();

            var st = conn.prepareStatement(sqlq);

            resetCounterTo(0);
            for (Object value : questionMarkValues) {
                switch (value.getClass().getName()) {
                    case "String":
                        st.setString(getCounter(), (String) value);
                        break;
                    case "int":
                        st.setInt(getCounter(), (int) value);
                        break;
                    default:
                        break;
                }
            }

            var rs = st.executeQuery();

            while (rs.next()) {
                @SuppressWarnings("unchecked")
				var inst = (T) constructorModel.newInstance();
                inst.setValues(rs);
                result_list.add(inst);
            }

            rs.close();
            st.close();
            return result_list;
        }
        catch (Exception e) {
            System.out.println("getCollection falhou: " + e.getMessage());
            e.getStackTrace();
        }

        return new ArrayList<T>();
    }

    public void sendCreate(Connection conn) throws SQLException {
        if (codigo > 0) {
            return; // ja existe
        }

        var sqlq = String.format("INSERT INTO %s %s VALUES %s", getTablename(), getOrder(), genInsertMarks());

        var st = conn.prepareStatement(sqlq, Statement.RETURN_GENERATED_KEYS);

        insertValues(st, 0);

        var rowsCreated = st.executeUpdate();

        System.out.println("rows created: " + rowsCreated);

        var rs = st.getGeneratedKeys();

        if (rowsCreated >= 1 && rs.next()) {
            this.codigo = rs.getInt("codigo");
            this.existsInDB = true;
        }

        rs.close();
        st.close();
    }

    public void sendUpdate(Connection conn) throws SQLException {
        if (this.codigo <= 0) {
            return; // não existe
        }

        var sqlq = String.format("UPDATE %s SET %s WHERE codigo = ?", getTablename(), genSetNames());

        var st = conn.prepareStatement(sqlq);

        insertValues(st, 0);
        st.setInt(getCounter(), codigo);

        int rowsUpdated = st.executeUpdate();

        System.out.println("rows updated: " + rowsUpdated);
    }

    public void sendDelete(Connection conn) throws SQLException {
        if (this.codigo <= 0) {
            return; // não existe
        }

        var sqlq = String.format("DELETE FROM %s WHERE codigo = ?", getTablename());

        var st = conn.prepareStatement(sqlq);

        st.setInt(1, codigo);

        int rowsDeleted = st.executeUpdate();

        System.out.println("rows deleted: " + rowsDeleted);

        this.existsInDB = false;
        this.codigo = 0;
    }

    protected int getCounter() {
        counter++;
        return counter;
    }

    protected void resetCounterTo(int number) {
        counter = number;
    }

    private String[] extractOrderData() {
        var order = getOrder();

        order = order.substring(1, order.length() - 1); // remover os ()

        return order.split(",");
    }

    private int orderDataCount() {
        return extractOrderData().length;
    }

    private String genInsertMarks() {
        var result = "(";

        for (int i = 1; i <= orderDataCount(); i++) {
            result += "?,";
        }

        result = result.substring(0, result.length() - 1);
        result += ");";

        return result;
    }

    private String genSetNames() {
        var result = "";
        var listNames = extractOrderData();

        for (String name : listNames) {
            result += name + " = ?,";
        }

        result = result.substring(0, result.length() - 1); // remover a ultima virgula

        return result;
    }
}