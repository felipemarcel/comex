package br.com.alura.comex.service;

import br.com.alura.comex.dto.ClienteDTO;
import br.com.alura.comex.model.Cliente;
import br.com.alura.comex.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    private ClienteRepository repository;

    @Autowired
    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    public Cliente save(Cliente cliente) {
        return repository.save(cliente);
    }

    public Page<ClienteDTO> list(Pageable pageable) {
        return repository.findAll(pageable).map(ClienteDTO::from);
    }

}
