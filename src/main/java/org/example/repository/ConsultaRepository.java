package org.example.repository;

import org.example.Model.Consulta;
import java.util.ArrayList;
import java.util.List;

public class ConsultaRepository {

    private static final List<Consulta> consultas = new ArrayList<>();
    private static int idCounter = 1;

    public void salvar(Consulta consulta) {
        consulta.setId(idCounter++);
        consultas.add(consulta);
    }

    public List<Consulta> listarTodas() {
        return new ArrayList<>(consultas);
    }

    public Consulta buscarPorId(int id) {
        return consultas.stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public boolean atualizar(int id, Consulta novaConsulta) {
        Consulta existente = buscarPorId(id);
        if (existente != null) {
            existente.setDataHora(novaConsulta.getDataHora());
            existente.setStatus(novaConsulta.getStatus());
            existente.setPacienteId(novaConsulta.getPacienteId());
            return true;
        }
        return false;
    }

    public boolean deletar(int id) {
        return consultas.removeIf(c -> c.getId() == id);
    }
}
