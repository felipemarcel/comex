package br.com.alura.comex.dto;

import br.com.alura.comex.model.Produto;

import java.math.BigDecimal;

public class ProdutoDTO {
    private String nome;
    private String descricao;
    private BigDecimal precoUnitario;
    private int quantidadeEstoque;
    private CategoriaDTO categoria;

    public ProdutoDTO(String nome, String descricao, BigDecimal precoUnitario, int quantidadeEstoque, CategoriaDTO categoria) {
        this.nome = nome;
        this.descricao = descricao;
        this.precoUnitario = precoUnitario;
        this.quantidadeEstoque = quantidadeEstoque;
        this.categoria = categoria;
    }

    private ProdutoDTO(Builder builder) {
        setNome(builder.nome);
        setDescricao(builder.descricao);
        setPrecoUnitario(builder.precoUnitario);
        setQuantidadeEstoque(builder.quantidadeEstoque);
        setCategoria(builder.categoria);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(BigDecimal precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(int quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public CategoriaDTO getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaDTO categoria) {
        this.categoria = categoria;
    }

    public static final class Builder {
        private String nome;
        private String descricao;
        private BigDecimal precoUnitario;
        private int quantidadeEstoque;
        private CategoriaDTO categoria;

        private Builder() {
        }

        public Builder nome(String nome) {
            this.nome = nome;
            return this;
        }

        public Builder descricao(String descricao) {
            this.descricao = descricao;
            return this;
        }

        public Builder precoUnitario(BigDecimal precoUnitario) {
            this.precoUnitario = precoUnitario;
            return this;
        }

        public Builder quantidadeEstoque(int quantidadeEstoque) {
            this.quantidadeEstoque = quantidadeEstoque;
            return this;
        }

        public Builder categoria(CategoriaDTO categoria) {
            this.categoria = categoria;
            return this;
        }

        public ProdutoDTO build() {
            return new ProdutoDTO(this);
        }
    }

    public static ProdutoDTO from(Produto produto) {
        return ProdutoDTO
                .newBuilder()
                .nome(produto.getNome())
                .descricao(produto.getDescricao())
                .precoUnitario(produto.getPrecoUnitario())
                .quantidadeEstoque(produto.getQuantidadeEstoque())
                .categoria(CategoriaDTO.from(produto.getCategoria()))
                .build();
    }
}
