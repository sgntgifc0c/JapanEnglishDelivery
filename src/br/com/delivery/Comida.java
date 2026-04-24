package br.com.delivery;

public class Comida extends Produto {
    private String categoria;

    public Comida(String nome, String descricao, double preco, String categoria) {
        super(nome, descricao, preco);
        this.categoria = categoria;
    }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    @Override
    public String toString() {
        return "[" + categoria + "] " + super.toString();
    }
}