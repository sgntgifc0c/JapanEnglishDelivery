package br.com.delivery;

public class Bebida extends Produto {
    private boolean alcoolica;

    public Bebida(String nome, String descricao, double preco, boolean alcoolica) {
        super(nome, descricao, preco);
        this.alcoolica = alcoolica;
    }

    public boolean isAlcoolica() { return alcoolica; }
    public void setAlcoolica(boolean alcoolica) { this.alcoolica = alcoolica; }

    @Override
    public String toString() {
        String aviso = alcoolica ? " (+18) " : " ";
        return "[Bebida]" + aviso + super.toString();
    }
}