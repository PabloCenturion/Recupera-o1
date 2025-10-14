package org.example;

import org.example.Controller.PacienteController;
import org.example.Controller.ConsultaController;
import org.example.repository.Database;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Database database = new Database();
        PacienteController pacienteController = new PacienteController(database);
        ConsultaController consultaController = new ConsultaController(database);
        Scanner scanner = new Scanner(System.in);

        int opcao;
        do {
            System.out.println("\n=== Sistema de Consultas ===");
            System.out.println("1 - CRUD Pacientes");
            System.out.println("2 - CRUD Consultas");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");
            opcao = Integer.parseInt(scanner.nextLine());

            switch (opcao) {
                case 1 -> pacienteController.menuPacientes();
                case 2 -> consultaController.menuConsultas();
                case 0 -> System.out.println("Saindo do sistema...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);

        scanner.close();
    }
}
