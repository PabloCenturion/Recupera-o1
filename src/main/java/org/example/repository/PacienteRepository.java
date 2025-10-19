package org.example.repository;

import org.example.Model.Paciente;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PacienteRepository {

    private static final List<Paciente> pacientes = new ArrayList<>();
    private static int idCounter = 1;

    public void salvar(Paciente paciente) {
        paciente.setId(idCounter++);
        pacientes.add(paciente);
    }

    public List<Paciente> listarTodos() {
        return new ArrayList<>(pacientes);
    }

    public Paciente buscarPorId(int id) {
        return pacientes.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public boolean atualizar(int id, Paciente novoPaciente) {
        Paciente existente = buscarPorId(id);
        if (existente != null) {
            existente.setNome(novoPaciente.getNome());
            existente.setCpf(novoPaciente.getCpf());
            existente.setEmail(novoPaciente.getEmail());
            existente.setTelefone(novoPaciente.getTelefone());
            existente.setEndereco(novoPaciente.getEndereco());
            return true;
        }
        return false;
    }

    public boolean deletar(int id) {
        return pacientes.removeIf(p -> p.getId() == id);
    }
}
