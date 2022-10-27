package br.com.alura.comex.dto;

import br.com.alura.comex.model.Categoria;

public record CategoriaDTO (String nome) {
    public static CategoriaDTO from(Categoria categoria) {
        return new CategoriaDTO(categoria.getNome());
    }
}

