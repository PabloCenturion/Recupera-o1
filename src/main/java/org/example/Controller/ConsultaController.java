package org.example.Controller;

import org.example.Model.Consulta;
import org.example.Model.Paciente;
import org.example.repository.Database;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class ConsultaController {

    private Database database;
    private Scanner scanner;

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public ConsultaController(Database database) {
        this.database = database;
        this.scanner = new Scanner(System.in);
    }

    public void menuConsultas() {
        int opcao;
        do {
            System.out.println("\n--- Menu Consultas ---");
            System.out.println("1 - Listar consultas");
            System.out.println("2 - Agendar consulta");
            System.out.println("3 - Editar consulta");
            System.out.println("4 - Remover consulta");
            System.out.println("5 - Confirmar consulta");
            System.out.println("6 - Cancelar consulta");
            System.out.println("7 - Reagendar consulta");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");
            opcao = Integer.parseInt(scanner.nextLine());

            switch (opcao) {
                case 1 -> listarConsultas();
                case 2 -> agendarConsulta();
                case 3 -> editarConsulta();
                case 4 -> removerConsulta();
                case 5 -> confirmarConsulta();
                case 6 -> cancelarConsulta();
                case 7 -> reagendarConsulta();
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    private void listarConsultas() {
        List<Consulta> consultas = database.listarConsultas();
        if (consultas.isEmpty()) {
            System.out.println("Nenhuma consulta agendada.");
        } else {
            consultas.forEach(System.out::println);
        }
    }

    private void agendarConsulta() {
        System.out.print("ID do paciente: ");
        int pacienteId = Integer.parseInt(scanner.nextLine());
        Paciente paciente = database.buscarPacientePorId(pacienteId);
        if (paciente == null) {
            System.out.println("Paciente não encontrado!");
            return;
        }

        System.out.print("Data e hora (dd/MM/yyyy HH:mm): ");
        String dataHoraStr = scanner.nextLine();
        LocalDateTime dataHora = LocalDateTime.parse(dataHoraStr, formatter);

        Consulta consulta = new Consulta(0, paciente.getId(), dataHora, "AGENDADA");
        database.adicionarConsulta(consulta);
        System.out.println("Consulta agendada com sucesso!");
    }

    private void editarConsulta() {
        System.out.print("ID da consulta a editar: ");
        int id = Integer.parseInt(scanner.nextLine());
        Consulta consulta = database.buscarConsultaPorId(id);
        if (consulta == null) {
            System.out.println("Consulta não encontrada!");
            return;
        }

        System.out.print("Nova data e hora (dd/MM/yyyy HH:mm) [" +
                consulta.getDataHora().format(formatter) + "]: ");
        String novaDataHoraStr = scanner.nextLine();
        if (!novaDataHoraStr.isEmpty()) {
            consulta.setDataHora(LocalDateTime.parse(novaDataHoraStr, formatter));
        }

        database.editarConsulta(id, consulta);
        System.out.println("Consulta editada com sucesso!");
    }

    private void removerConsulta() {
        System.out.print("ID da consulta a remover: ");
        int id = Integer.parseInt(scanner.nextLine());
        if (database.removerConsulta(id)) {
            System.out.println("Consulta removida com sucesso!");
        } else {
            System.out.println("Consulta não encontrada.");
        }
    }

    private void confirmarConsulta() {
        System.out.print("ID da consulta a confirmar: ");
        int id = Integer.parseInt(scanner.nextLine());
        Consulta consulta = database.buscarConsultaPorId(id);
        if (consulta != null) {
            consulta.setStatus("CONFIRMADA");
            database.editarConsulta(id, consulta);
            System.out.println("Consulta confirmada!");
        } else {
            System.out.println("Consulta não encontrada.");
        }
    }

    private void cancelarConsulta() {
        System.out.print("ID da consulta a cancelar: ");
        int id = Integer.parseInt(scanner.nextLine());
        Consulta consulta = database.buscarConsultaPorId(id);
        if (consulta != null) {
            consulta.setStatus("CANCELADA");
            database.editarConsulta(id, consulta);
            System.out.println("Consulta cancelada!");
        } else {
            System.out.println("Consulta não encontrada.");
        }
    }

    private void reagendarConsulta() {
        System.out.print("ID da consulta a reagendar: ");
        int id = Integer.parseInt(scanner.nextLine());
        Consulta consulta = database.buscarConsultaPorId(id);
        if (consulta == null) {
            System.out.println("Consulta não encontrada!");
            return;
        }

        System.out.print("Nova data e hora (dd/MM/yyyy HH:mm): ");
        String novaDataHoraStr = scanner.nextLine();
        LocalDateTime novaDataHora = LocalDateTime.parse(novaDataHoraStr, formatter);

        consulta.setDataHora(novaDataHora);
        consulta.setStatus("AGENDADA");
        database.editarConsulta(id, consulta);
        System.out.println("Consulta reagendada com sucesso!");
    }
}
