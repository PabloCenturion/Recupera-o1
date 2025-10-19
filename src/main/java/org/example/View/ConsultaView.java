package org.example.View;

import org.example.Model.Consulta;
import org.example.Controller.ConsultaController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class ConsultaView {

    private final Scanner sc = new Scanner(System.in);
    private final ConsultaController consultaController = new ConsultaController();

    public void exibirMenu() {
        int opcao;
        do {
            System.out.println("\n=== CRUD CONSULTAS ===");
            System.out.println("1. Agendar Consulta");
            System.out.println("2. Listar Consultas");
            System.out.println("3. Editar Consulta");
            System.out.println("4. Cancelar Consulta");
            System.out.println("5. Confirmar Consulta");
            System.out.println("6. Reagendar Consulta");
            System.out.println("0. Voltar");
            System.out.print("Escolha: ");
            opcao = Integer.parseInt(sc.nextLine());

            switch (opcao) {
                case 1 -> agendarConsulta();
                case 2 -> listarConsultas();
                case 3 -> editarConsulta();
                case 4 -> cancelarConsulta();
                case 5 -> confirmarConsulta();
                case 6 -> reagendarConsulta();
                case 0 -> {}
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    private void agendarConsulta() {
        System.out.print("ID do Paciente: ");
        int pacienteId = Integer.parseInt(sc.nextLine());
        System.out.print("Data e hora (ex: 2025-10-20 15:30): ");
        String dataHoraStr = sc.nextLine();
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime dataHora = LocalDateTime.parse(dataHoraStr, formatter);

            Consulta c = new Consulta(0, pacienteId, dataHora, "PENDENTE");
            consultaController.adicionarConsulta(c);

            System.out.println("✅ Consulta agendada com sucesso!");
        } catch (DateTimeParseException e) {
            System.out.println("❌ Erro: formato de data/hora inválido. Use o padrão yyyy-MM-dd HH:mm");
        }
    }

    private void listarConsultas() {
        List<Consulta> consultas = consultaController.listarConsultas();
        consultas.forEach(System.out::println);
    }

    private void editarConsulta() {
        System.out.print("ID da Consulta: ");
        int id = Integer.parseInt(sc.nextLine());

        System.out.print("Nova data e hora (ex: 2025-10-20 15:30): ");
        String novaDataHoraStr = sc.nextLine();

        System.out.print("Novo status: ");
        String status = sc.nextLine();

        System.out.print("ID do paciente: ");
        int pacienteId = Integer.parseInt(sc.nextLine());

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime novaDataHora = LocalDateTime.parse(novaDataHoraStr, formatter);

            Consulta nova = new Consulta(id, pacienteId, novaDataHora, status);
            boolean sucesso = consultaController.editarConsulta(id, nova);

            if (sucesso)
                System.out.println("✅ Consulta editada com sucesso!");
            else
                System.out.println("❌ Consulta não encontrada!");
        } catch (DateTimeParseException e) {
            System.out.println("❌ Erro: formato de data/hora inválido. Use o padrão yyyy-MM-dd HH:mm");
        }
    }


    private void cancelarConsulta() {
        System.out.print("ID da Consulta: ");
        int id = Integer.parseInt(sc.nextLine());
        consultaController.cancelarConsulta(id);
    }

    private void confirmarConsulta() {
        System.out.print("ID da Consulta: ");
        int id = Integer.parseInt(sc.nextLine());
        consultaController.confirmarConsulta(id);
    }

    private void reagendarConsulta() {
        System.out.print("ID da Consulta: ");
        int id = Integer.parseInt(sc.nextLine());
        System.out.print("Nova data e hora: ");
        String novaDataHora = sc.nextLine();
        consultaController.reagendarConsulta(id, novaDataHora);
    }
}
