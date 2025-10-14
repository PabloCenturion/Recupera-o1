package org.example.Controller;

import org.example.Model.Paciente;
import org.example.Model.Endereco;
import org.example.repository.Database;

import java.util.List;
import java.util.Scanner;

public class PacienteController {

    private Database database;
    private Scanner scanner;

    public PacienteController(Database database) {
        this.database = database;
        this.scanner = new Scanner(System.in);
    }

    public void menuPacientes() {
        int opcao;
        do {
            System.out.println("\n--- Menu Pacientes ---");
            System.out.println("1 - Listar pacientes");
            System.out.println("2 - Adicionar paciente");
            System.out.println("3 - Editar paciente");
            System.out.println("4 - Remover paciente");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");
            opcao = Integer.parseInt(scanner.nextLine());

            switch (opcao) {
                case 1 -> listarPacientes();
                case 2 -> adicionarPaciente();
                case 3 -> editarPaciente();
                case 4 -> removerPaciente();
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    private void listarPacientes() {
        List<Paciente> pacientes = database.listarPacientes();
        if (pacientes.isEmpty()) {
            System.out.println("Nenhum paciente cadastrado.");
        } else {
            pacientes.forEach(System.out::println);
        }
    }

    private void adicionarPaciente() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();

        System.out.println("--- Endereço ---");
        System.out.print("Estado: ");
        String estado = scanner.nextLine();
        System.out.print("Cidade: ");
        String cidade = scanner.nextLine();
        System.out.print("Rua: ");
        String rua = scanner.nextLine();
        System.out.print("Número: ");
        String numero = scanner.nextLine();
        System.out.print("CEP: ");
        String cep = scanner.nextLine();

        Endereco endereco = new Endereco(0, estado, cidade, rua, numero, cep);
        Paciente paciente = new Paciente(0, nome, cpf, email, telefone, endereco);


        database.adicionarPaciente(paciente);
        System.out.println("Paciente adicionado com sucesso!");
    }

    private void editarPaciente() {
        System.out.print("ID do paciente a editar: ");
        int id = Integer.parseInt(scanner.nextLine());
        Paciente existente = database.buscarPacientePorId(id);
        if (existente == null) {
            System.out.println("Paciente não encontrado!");
            return;
        }

        System.out.print("Nome (" + existente.getNome() + "): ");
        String nome = scanner.nextLine();
        if (!nome.isEmpty()) existente.setNome(nome);

        System.out.print("CPF (" + existente.getCpf() + "): ");
        String cpf = scanner.nextLine();
        if (!cpf.isEmpty()) existente.setCpf(cpf);

        System.out.print("Email (" + existente.getEmail() + "): ");
        String email = scanner.nextLine();
        if (!email.isEmpty()) existente.setEmail(email);

        System.out.print("Telefone (" + existente.getTelefone() + "): ");
        String telefone = scanner.nextLine();
        if (!telefone.isEmpty()) existente.setTelefone(telefone);

        // Editando endereço
        Endereco endereco = existente.getEndereco();
        System.out.print("Estado (" + endereco.getEstado() + "): ");
        String estado = scanner.nextLine();
        if (!estado.isEmpty()) endereco.setEstado(estado);

        System.out.print("Cidade (" + endereco.getCidade() + "): ");
        String cidade = scanner.nextLine();
        if (!cidade.isEmpty()) endereco.setCidade(cidade);

        System.out.print("Rua (" + endereco.getRua() + "): ");
        String rua = scanner.nextLine();
        if (!rua.isEmpty()) endereco.setRua(rua);

        System.out.print("Número (" + endereco.getNumero() + "): ");
        String numero = scanner.nextLine();
        if (!numero.isEmpty()) endereco.setNumero(numero);

        System.out.print("CEP (" + endereco.getCep() + "): ");
        String cep = scanner.nextLine();
        if (!cep.isEmpty()) endereco.setCep(cep);

        database.editarPaciente(id, existente);
        System.out.println("Paciente editado com sucesso!");
    }

    private void removerPaciente() {
        System.out.print("ID do paciente a remover: ");
        int id = Integer.parseInt(scanner.nextLine());
        if (database.removerPaciente(id)) {
            System.out.println("Paciente removido com sucesso!");
        } else {
            System.out.println("Paciente não encontrado.");
        }
    }
}
