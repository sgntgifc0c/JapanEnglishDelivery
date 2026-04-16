package delivery.model;

public class Cliente {
    private String nome;
    private String cpf;
    private String telefone;
    private String endereco;

    public Cliente(String nome, String cpf, String telefone, String endereco) {
        setNome(nome);
        setCpf(cpf);
        setTelefone(telefone);
        setEndereco(endereco);
    }

    public String getNome() { return nome; }
    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome e obrigatorio.");
        }
        this.nome = nome;
    }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) {
        if (cpf == null || cpf.length() < 11) {
            throw new IllegalArgumentException("CPF invalido. Deve conter pelo menos 11 digitos.");
        }
        this.cpf = cpf;
    }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) {
        if (endereco == null || endereco.trim().isEmpty()) {
            throw new IllegalArgumentException("Endereco e obrigatorio para entrega.");
        }
        this.endereco = endereco;
    }

    @Override
    public String toString() {
        return "Cliente: " + nome + " | CPF: " + cpf + "\nEndereco: " + endereco;
    }
}