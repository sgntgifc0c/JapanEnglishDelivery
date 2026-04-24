package br.com.delivery;

public class Sobremesa extends Produto {
    private boolean paraDividir;

    public Sobremesa(String nome, String descricao, double preco, boolean paraDividir) {
        super(nome, descricao, preco);
        this.paraDividir = paraDividir;
    }

    public boolean isParaDividir() { return paraDividir; }
    public void setParaDividir(boolean paraDividir) { this.paraDividir = paraDividir; }

    @Override
    public String toString() {
        String aviso = paraDividir ? " (Tamanho Familia) " : " ";
        return "[Sobremesa]" + aviso + super.toString();
    }
}