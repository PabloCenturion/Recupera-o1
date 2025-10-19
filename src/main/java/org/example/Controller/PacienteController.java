package org.example.Controller;

import org.example.Model.Paciente;
import org.example.Model.Endereco;
import org.example.repository.PacienteRepository;

import java.util.List;

public class PacienteController {

    private final PacienteRepository pacienteRepository = new PacienteRepository();

    public void adicionarPaciente(Paciente paciente) {
        pacienteRepository.salvar(paciente);
        System.out.println("✅ Paciente adicionado com sucesso!");
    }

    public List<Paciente> listarPacientes() {
        List<Paciente> lista = pacienteRepository.listarTodos();
        if (lista.isEmpty()) {
            System.out.println("⚠️ Nenhum paciente cadastrado.");
        }
        return lista;
    }

    public void editarPaciente(int id, Paciente pacienteAtualizado) {
        boolean sucesso = pacienteRepository.atualizar(id, pacienteAtualizado);
        if (sucesso)
            System.out.println("✅ Paciente atualizado com sucesso!");
        else
            System.out.println("❌ Paciente não encontrado.");
    }

    public void removerPaciente(int id) {
        boolean sucesso = pacienteRepository.deletar(id);
        if (sucesso)
            System.out.println("✅ Paciente removido com sucesso!");
        else
            System.out.println("❌ Paciente não encontrado.");
    }

    public Paciente buscarPorId(int id) {
        return pacienteRepository.buscarPorId(id);
    }
}
