package br.com.alura.comex.service;

import br.com.alura.comex.exception.NotFoundException;
import br.com.alura.comex.model.Pedido;
import br.com.alura.comex.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

    private PedidoRepository repository;

    @Autowired
    public PedidoService(PedidoRepository repository) {
        this.repository = repository;
    }

    public Pedido save(Pedido pedido) {
        return repository.save(pedido);
    }

    public Page<Pedido> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Pedido findById(Long id) {
        return repository.findById(id).orElseThrow(() -> NotFoundException.notFoundException(id));
    }

    public Pedido update(Pedido pedido, Long id) {
        Pedido pedidoRecuperado = this.findById(id);
        return repository.save(pedidoRecuperado);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
