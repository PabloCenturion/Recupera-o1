package org.example.View;

import org.example.Controller.EnderecoController;
import org.example.Model.Endereco;
import org.example.Controller.EnderecoController;

import java.util.List;
import java.util.Scanner;

public class EnderecoView {

    private final Scanner sc = new Scanner(System.in);
    private final EnderecoController enderecoController = new EnderecoController();

    public void exibirMenu() {
        int opcao;
        do {
            System.out.println("\n=== CRUD ENDEREÇOS ===");
            System.out.println("1. Cadastrar Endereço");
            System.out.println("2. Listar Endereços");
            System.out.println("3. Editar Endereço");
            System.out.println("4. Remover Endereço");
            System.out.println("0. Voltar");
            System.out.print("Escolha: ");
            opcao = Integer.parseInt(sc.nextLine());

            switch (opcao) {
                case 1 -> cadastrarEndereco();
                case 2 -> listarEnderecos();
                case 3 -> editarEndereco();
                case 4 -> removerEndereco();
                case 0 -> {}
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    private void cadastrarEndereco() {
        System.out.print("ID do Paciente: ");
        int pacienteId = Integer.parseInt(sc.nextLine());
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

        Endereco e = new Endereco(0, pacienteId, estado, cidade, rua, numero, cep);
        enderecoController.adicionarEndereco(e);
    }

    private void listarEnderecos() {
        List<Endereco> enderecos = enderecoController.listarEnderecos();
        enderecos.forEach(System.out::println);
    }

    private void editarEndereco() {
        System.out.print("ID do Endereço: ");
        int id = Integer.parseInt(sc.nextLine());
        System.out.print("Novo estado: ");
        String estado = sc.nextLine();
        System.out.print("Nova cidade: ");
        String cidade = sc.nextLine();
        System.out.print("Nova rua: ");
        String rua = sc.nextLine();
        System.out.print("Novo número: ");
        String numero = sc.nextLine();
        System.out.print("Novo CEP: ");
        String cep = sc.nextLine();

        Endereco novo = new Endereco(id, 0, estado, cidade, rua, numero, cep);
        enderecoController.editarEndereco(id, novo);
    }

    private void removerEndereco() {
        System.out.print("ID do Endereço: ");
        int id = Integer.parseInt(sc.nextLine());
        enderecoController.removerEndereco(id);
    }
}
