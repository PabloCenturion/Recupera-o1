package org.example.Controller;

import org.example.Model.Consulta;
import org.example.Model.Paciente;
import org.example.repository.ConsultaRepository;
import org.example.repository.PacienteRepository;

import java.time.LocalDateTime;
import java.util.List;

public class ConsultaController {

    private final ConsultaRepository consultaRepository = new ConsultaRepository();
    private final PacienteRepository pacienteRepository = new PacienteRepository();

    public void adicionarConsulta(Consulta consulta) {
        Paciente paciente = pacienteRepository.buscarPorId(consulta.getPacienteId());
        if (paciente == null) {
            System.out.println("❌ Paciente não encontrado. Não é possível criar a consulta.");
            return;
        }
        consultaRepository.salvar(consulta);
        System.out.println("✅ Consulta adicionada com sucesso!");
    }

    public List<Consulta> listarConsultas() {
        List<Consulta> lista = consultaRepository.listarTodas();
        if (lista.isEmpty()) {
            System.out.println("⚠️ Nenhuma consulta cadastrada.");
        }
        return lista;
    }

    public boolean editarConsulta(int id, Consulta novaConsulta) {
        boolean sucesso = consultaRepository.atualizar(id, novaConsulta);
        if (sucesso)
            System.out.println("✅ Consulta atualizada com sucesso!");
        else
            System.out.println("❌ Consulta não encontrada.");
        return sucesso;
    }

    public void removerConsulta(int id) {
        boolean sucesso = consultaRepository.deletar(id);
        if (sucesso)
            System.out.println("✅ Consulta removida com sucesso!");
        else
            System.out.println("❌ Consulta não encontrada.");
    }

    public void confirmarConsulta(int id) {
        Consulta c = consultaRepository.buscarPorId(id);
        if (c != null) {
            c.setStatus(Consulta.Status.valueOf("Confirmada"));
            System.out.println("✅ Consulta confirmada!");
        } else {
            System.out.println("❌ Consulta não encontrada.");
        }
    }

    public void cancelarConsulta(int id) {
        Consulta c = consultaRepository.buscarPorId(id);
        if (c != null) {
            c.setStatus(Consulta.Status.valueOf("Cancelada"));
            System.out.println("❌ Consulta cancelada!");
        } else {
            System.out.println("⚠️ Consulta não encontrada.");
        }
    }

    public void reagendarConsulta(int id, String novaDataHora) {
        Consulta c = consultaRepository.buscarPorId(id);
        if (c != null) {
            c.setDataHora(LocalDateTime.parse(novaDataHora));
            c.setStatus(Consulta.Status.valueOf("Reagendada"));
            System.out.println("🔄 Consulta reagendada para: " + novaDataHora);
        } else {
            System.out.println("❌ Consulta não encontrada.");
        }
    }
}
