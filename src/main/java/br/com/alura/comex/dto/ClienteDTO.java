package br.com.alura.comex.dto;

import br.com.alura.comex.model.Cliente;

public record ClienteDTO(String nome, String cpf, String telefone, String Local) {

    public static ClienteDTO from(Cliente cliente) {
        return new ClienteDTO(cliente.getNome(), cliente.getCpf(), cliente.getTelefone(), String.join("/", cliente.getCidade(), cliente.getEstado()));
    }
}
