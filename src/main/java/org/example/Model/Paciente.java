package org.example.Model;

import java.io.Serializable;
import java.util.Objects;

/**
 * Entidade Paciente (usuário).
 */
public class Paciente implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String nome;
    private String cpf;
    private String email;
    private String telefone;

    public Paciente() {}

    public Paciente(int id, String nome, String cpf, String email, String telefone) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
    }


    private Endereco endereco;
    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }


    // getters e setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    @Override
    public String toString() {
        return String.format("[ID:%d] %s | CPF:%s | Email:%s | Tel:%s", id, nome, cpf, email, telefone);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Paciente)) return false;
        Paciente paciente = (Paciente) o;
        return id == paciente.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
