package delivery.model;

public class ItemPedido {
    private Produto produto;
    private int quantidade;
    private String observacao;

    public ItemPedido(Produto produto, int quantidade, String observacao) {
        setProduto(produto);
        setQuantidade(quantidade);
        setObservacao(observacao);
    }

    public Produto getProduto() { return produto; }
    public void setProduto(Produto produto) {
        if (produto == null) {
            throw new IllegalArgumentException("Produto invalido.");
        }
        this.produto = produto;
    }

    public int getQuantidade() { return quantidade; }
    public void setQuantidade(int quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("A quantidade deve ser de pelo menos 1.");
        }
        this.quantidade = quantidade;
    }

    public String getObservacao() { return observacao; }
    public void setObservacao(String observacao) {
        this.observacao = observacao != null && !observacao.trim().isEmpty() ? observacao : "Sem observacoes";
    }

    public double getSubtotal() {
        return produto.getPreco() * quantidade;
    }

    @Override
    public String toString() {
        return quantidade + "x " + produto.getNome() + " - R$ " + String.format("%.2f", getSubtotal()) + 
               " (Obs: " + observacao + ")";
    }
}