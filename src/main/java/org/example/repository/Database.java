package org.example.repository;

import org.example.Model.Paciente;
import org.example.Model.Endereco;
import org.example.Model.Consulta;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Database {

    private List<Paciente> pacientes = new ArrayList<>();
    private List<Consulta> consultas = new ArrayList<>();

    private int pacienteIdCounter = 1;
    private int consultaIdCounter = 1;
    private int enderecoIdCounter = 1;

    // ---------- CRUD PACIENTE ----------
    public void adicionarPaciente(Paciente paciente) {
        paciente.setId(pacienteIdCounter++);
        if (paciente.getEndereco() != null) {
            paciente.getEndereco().setId(enderecoIdCounter++);
        }
        pacientes.add(paciente);
    }

    public List<Paciente> listarPacientes() {
        return new ArrayList<>(pacientes);
    }

    public Paciente buscarPacientePorId(int id) {
        Optional<Paciente> opt = pacientes.stream().filter(p -> p.getId() == id).findFirst();
        return opt.orElse(null);
    }

    public boolean editarPaciente(int id, Paciente novoPaciente) {
        Paciente existente = buscarPacientePorId(id);
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

    public boolean removerPaciente(int id) {
        return pacientes.removeIf(p -> p.getId() == id);
    }

    // ---------- CRUD CONSULTA ----------
    public void adicionarConsulta(Consulta consulta) {
        consulta.setId(consultaIdCounter++);
        consultas.add(consulta);
    }

    public List<Consulta> listarConsultas() {
        return new ArrayList<>(consultas);
    }

    public Consulta buscarConsultaPorId(int id) {
        Optional<Consulta> opt = consultas.stream().filter(c -> c.getId() == id).findFirst();
        return opt.orElse(null);
    }

    public boolean editarConsulta(int id, Consulta novaConsulta) {
        Consulta existente = buscarConsultaPorId(id);
        if (existente != null) {
            existente.setDataHora(novaConsulta.getDataHora());
            existente.setStatus(novaConsulta.getStatus());
            existente.setPacienteId(novaConsulta.getPacienteId());
            return true;
        }
        return false;
    }

    public boolean removerConsulta(int id) {
        return consultas.removeIf(c -> c.getId() == id);
    }
}
