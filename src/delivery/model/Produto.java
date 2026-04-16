package delivery.model;

public class Produto {
    private String nome;
    private String descricao;
    private double preco;

    public Produto(String nome, String descricao, double preco) {
        setNome(nome);
        setDescricao(descricao);
        setPreco(preco);
    }

    public String getNome() { return nome; }
    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do produto nao pode ser vazio.");
        }
        this.nome = nome;
    }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public double getPreco() { return preco; }
    public void setPreco(double preco) {
        if (preco <= 0) {
            throw new IllegalArgumentException("O preco deve ser maior que zero.");
        }
        this.preco = preco;
    }

    @Override
    public String toString() {
        return nome + " (R$ " + String.format("%.2f", preco) + ") - " + descricao;
    }
}