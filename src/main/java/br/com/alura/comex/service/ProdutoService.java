package br.com.alura.comex.service;

import br.com.alura.comex.model.Produto;
import br.com.alura.comex.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class ProdutoService {

    private ProdutoRepository repository;

    @Autowired
    public ProdutoService(ProdutoRepository produtoRepository) {
        this.repository = produtoRepository;
    }

    public Page<Produto> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Produto save(Produto produto) {
        return repository.save(produto);
    }
}
