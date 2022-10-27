package br.com.alura.comex.service;

import br.com.alura.comex.model.Categoria;
import br.com.alura.comex.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoriaService {

    private CategoriaRepository repository;

    @Autowired
    public CategoriaService(CategoriaRepository repository) {
        this.repository = repository;
    }

    public Categoria save(Categoria categoria) {
        return repository.save(categoria);
    }

    public Page<Categoria> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Optional<Categoria> findById(Long id) {
        return repository.findById(id);
    }
}
