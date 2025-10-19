package org.example.repository;

import org.example.Model.Endereco;
import java.util.ArrayList;
import java.util.List;

public class EnderecoRepository {

    private static final List<Endereco> enderecos = new ArrayList<>();
    private static int idCounter = 1;

    public void salvar(Endereco endereco) {
        endereco.setId(idCounter++);
        enderecos.add(endereco);
    }

    public List<Endereco> listarTodos() {
        return new ArrayList<>(enderecos);
    }

    public Endereco buscarPorId(int id) {
        return enderecos.stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public boolean atualizar(int id, Endereco novoEndereco) {
        Endereco existente = buscarPorId(id);
        if (existente != null) {
            existente.setEstado(novoEndereco.getEstado());
            existente.setCidade(novoEndereco.getCidade());
            existente.setRua(novoEndereco.getRua());
            existente.setNumero(novoEndereco.getNumero());
            existente.setCep(novoEndereco.getCep());
            existente.setPacienteId(novoEndereco.getPacienteId());
            return true;
        }
        return false;
    }

    public boolean deletar(int id) {
        return enderecos.removeIf(e -> e.getId() == id);
    }
}
