package org.example.View;

import java.util.Scanner;

public class MenuPrincipalView {

    private final Scanner sc = new Scanner(System.in);
    private final PacienteView pacienteView = new PacienteView();
    private final EnderecoView enderecoView = new EnderecoView();
    private final ConsultaView consultaView = new ConsultaView();

    public void exibirMenu() {
        int opcao;
        do {
            System.out.println("\n=== SISTEMA DE CONSULTAS ===");
            System.out.println("1. CRUD Pacientes");
            System.out.println("2. CRUD Endereços");
            System.out.println("3. CRUD Consultas");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = Integer.parseInt(sc.nextLine());

            switch (opcao) {
                case 1 -> pacienteView.exibirMenu();
                case 2 -> enderecoView.exibirMenu();
                case 3 -> consultaView.exibirMenu();
                case 0 -> System.out.println("Encerrando o sistema...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }
}
