package org.example.Model;

import java.io.Serializable;
import java.util.Objects;

/**
 * Entidade Endereco vinculada a um Paciente (por pacienteId).
 */
public class Endereco implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private int pacienteId;
    private String estado;
    private String cidade;
    private String rua;
    private String numero;
    private String cep;

    public Endereco() {}

    public Endereco(int id, int pacienteId, String estado, String cidade, String rua, String numero, String cep) {
        this.id = id;
        this.pacienteId = pacienteId;
        this.estado = estado;
        this.cidade = cidade;
        this.rua = rua;
        this.numero = numero;
        this.cep = cep;
    }

    // getters e setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getPacienteId() { return pacienteId; }
    public void setPacienteId(int pacienteId) { this.pacienteId = pacienteId; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public String getCidade() { return cidade; }
    public void setCidade(String cidade) { this.cidade = cidade; }

    public String getRua() { return rua; }
    public void setRua(String rua) { this.rua = rua; }

    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }

    public String getCep() { return cep; }
    public void setCep(String cep) { this.cep = cep; }

    @Override
    public String toString() {
        return String.format("[ID:%d] PacienteID:%d | %s, %s, %s, %s | CEP:%s", id, pacienteId, rua, numero, cidade, estado, cep);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Endereco)) return false;
        Endereco endereco = (Endereco) o;
        return id == endereco.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
