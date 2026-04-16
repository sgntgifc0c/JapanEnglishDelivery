package delivery.model;

public class Avaliacao {
    private Cliente cliente;
    private int nota;
    private String comentario;

    public Avaliacao(Cliente cliente, int nota, String comentario) {
        setCliente(cliente);
        setNota(nota);
        setComentario(comentario);
    }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) {
        if (cliente == null) {
            throw new IllegalArgumentException("Cliente nao pode ser nulo na avaliacao.");
        }
        this.cliente = cliente;
    }

    public int getNota() { return nota; }
    public void setNota(int nota) {
        if (nota < 1 || nota > 5) {
            throw new IllegalArgumentException("A nota deve ser entre 1 e 5 estrelas.");
        }
        this.nota = nota;
    }

    public String getComentario() { return comentario; }
    public void setComentario(String comentario) {
        this.comentario = (comentario == null || comentario.trim().isEmpty()) 
                          ? "Sem comentario" : comentario;
    }

    @Override
    public String toString() {
        return "Avaliacao de " + cliente.getNome() + ": " + nota + " estrelas - \"" + comentario + "\"";
    }
}
