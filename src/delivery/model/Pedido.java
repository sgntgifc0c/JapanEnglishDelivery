package delivery.model;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private Cliente cliente;
    private List<ItemPedido> itens;
    private String status;

    public Pedido(Cliente cliente) {
        setCliente(cliente);
        this.itens = new ArrayList<>();
        setStatus("Aberto");
    }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) {
        if (cliente == null) {
            throw new IllegalArgumentException("Um pedido precisa estar vinculado a um cliente.");
        }
        this.cliente = cliente;
    }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public void adicionarItem(ItemPedido item) {
        if (item != null) {
            this.itens.add(item);
        }
    }

    public List<ItemPedido> getItens() { return itens; }

    public double calcularTotal() {
        double total = 0;
        for (ItemPedido item : itens) {
            total += item.getSubtotal();
        }

        if (total > 200.00) {
            total = total - (total * 0.15); // 15% de desconto
        } else if (total > 100.00) {
            total = total - (total * 0.05); // 5% de desconto
        }

        return total;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("--- Resumo do Pedido ---\n");
        sb.append(cliente.toString()).append("\nItens:\n");
        for (ItemPedido item : itens) {
            sb.append("- ").append(item.toString()).append("\n");
        }
        sb.append("Status: ").append(status).append("\n");
        sb.append("Total Final (com possiveis descontos): R$ ").append(String.format("%.2f", calcularTotal()));
        return sb.toString();
    }
}