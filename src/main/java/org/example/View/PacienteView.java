package org.example.View;

import org.example.Model.Paciente;
import org.example.Model.Endereco;
import org.example.Controller.PacienteController;

import java.util.List;
import java.util.Scanner;

public class PacienteView {

    private final Scanner sc = new Scanner(System.in);
    private final PacienteController pacienteController = new PacienteController();

    public void exibirMenu() {
        int opcao;
        do {
            System.out.println("\n=== CRUD PACIENTES ===");
            System.out.println("1. Cadastrar Paciente");
            System.out.println("2. Listar Pacientes");
            System.out.println("3. Editar Paciente");
            System.out.println("4. Remover Paciente");
            System.out.println("0. Voltar");
            System.out.print("Escolha: ");
            opcao = Integer.parseInt(sc.nextLine());

            switch (opcao) {
                case 1 -> cadastrarPaciente();
                case 2 -> listarPacientes();
                case 3 -> editarPaciente();
                case 4 -> removerPaciente();
                case 0 -> {}
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    private void cadastrarPaciente() {
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("CPF: ");
        String cpf = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Telefone: ");
        String telefone = sc.nextLine();

        System.out.println("---- Endereço ----");
        System.out.print("Estado: ");
        String estado = sc.nextLine();
        System.out.print("Cidade: ");
        String cidade = sc.nextLine();
        System.out.print("Rua: ");
        String rua = sc.nextLine();
        System.out.print("Número: ");
        String numero = sc.nextLine();
        System.out.print("CEP: ");
        String cep = sc.nextLine();

        Paciente paciente = new Paciente(0, nome, cpf, email, telefone);
        Endereco endereco = new Endereco(0, 0, estado, cidade, rua, numero, cep);
        paciente.setEndereco(endereco);

        pacienteController.adicionarPaciente(paciente);
    }

    private void listarPacientes() {
        List<Paciente> pacientes = pacienteController.listarPacientes();
        pacientes.forEach(System.out::println);
    }

    private void editarPaciente() {
        System.out.print("Digite o ID do paciente: ");
        int id = Integer.parseInt(sc.nextLine());
        System.out.print("Novo nome: ");
        String nome = sc.nextLine();
        System.out.print("Novo CPF: ");
        String cpf = sc.nextLine();
        System.out.print("Novo email: ");
        String email = sc.nextLine();
        System.out.print("Novo telefone: ");
        String telefone = sc.nextLine();

        Paciente novo = new Paciente(id, nome, cpf, email, telefone);
        pacienteController.editarPaciente(id, novo);
    }

    private void removerPaciente() {
        System.out.print("Digite o ID do paciente a remover: ");
        int id = Integer.parseInt(sc.nextLine());
        pacienteController.removerPaciente(id);
    }
}
