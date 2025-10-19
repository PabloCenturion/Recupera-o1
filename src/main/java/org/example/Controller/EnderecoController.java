package org.example.Controller;

import org.example.Model.Endereco;
import org.example.repository.EnderecoRepository;

import java.util.List;

public class EnderecoController {

    private final EnderecoRepository enderecoRepository = new EnderecoRepository();

    public void adicionarEndereco(Endereco endereco) {
        enderecoRepository.salvar(endereco);
        System.out.println("✅ Endereço adicionado com sucesso!");
    }

    public List<Endereco> listarEnderecos() {
        List<Endereco> lista = enderecoRepository.listarTodos();
        if (lista.isEmpty()) {
            System.out.println("⚠️ Nenhum endereço cadastrado.");
        }
        return lista;
    }

    public void editarEndereco(int id, Endereco novoEndereco) {
        boolean sucesso = enderecoRepository.atualizar(id, novoEndereco);
        if (sucesso)
            System.out.println("✅ Endereço atualizado com sucesso!");
        else
            System.out.println("❌ Endereço não encontrado.");
    }

    public void removerEndereco(int id) {
        boolean sucesso = enderecoRepository.deletar(id);
        if (sucesso)
            System.out.println("✅ Endereço removido com sucesso!");
        else
            System.out.println("❌ Endereço não encontrado.");
    }

    public Endereco buscarPorId(int id) {
        return enderecoRepository.buscarPorId(id);
    }
}
