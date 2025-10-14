package org.example.Model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Entidade Consulta vinculada a um Paciente (por pacienteId).
 * Usa LocalDateTime para data/hora.
 */
public class Consulta implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private int pacienteId;
    private LocalDateTime dataHora;
    private Status status;

    public enum Status {
        AGENDADA,
        CONFIRMADA,
        CANCELADA
    }

    public Consulta() {}

    public Consulta(int id, int pacienteId, LocalDateTime dataHora) {
        this.id = id;
        this.pacienteId = pacienteId;
        this.dataHora = dataHora;
        this.status = Status.AGENDADA;
    }

    // getters e setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getPacienteId() { return pacienteId; }
    public void setPacienteId(int pacienteId) { this.pacienteId = pacienteId; }

    public LocalDateTime getDataHora() { return dataHora; }
    public void setDataHora(LocalDateTime dataHora) { this.dataHora = dataHora; }

    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }

    @Override
    public String toString() {
        String dh = (dataHora == null) ? "N/A" : dataHora.toString().replace('T', ' ');
        return String.format("[ID:%d] PacienteID:%d | %s | %s", id, pacienteId, dh, status);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Consulta)) return false;
        Consulta consulta = (Consulta) o;
        return id == consulta.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
