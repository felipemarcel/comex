package br.com.alura.comex.dto;

import br.com.alura.comex.model.Categoria;

public class CategoriaDTO {
    public String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public CategoriaDTO(String nome) {
        this.nome = nome;
    }

    public static CategoriaDTO from(Categoria categoria) {
        return new CategoriaDTO(categoria.getNome());
    }
}
