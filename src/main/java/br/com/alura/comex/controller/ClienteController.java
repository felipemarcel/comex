package br.com.alura.comex.controller;

import br.com.alura.comex.dto.ClienteDTO;
import br.com.alura.comex.model.Cliente;
import br.com.alura.comex.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

import static org.springframework.http.ResponseEntity.created;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService service;

    @PostMapping
    public ResponseEntity<Cliente> save(@RequestBody @Valid Cliente cliente, UriComponentsBuilder uriBuilder) {
        Cliente clienteSalvo = service.save(cliente);
        URI uri = uriBuilder.path("/clientes/{id}").buildAndExpand(clienteSalvo.getId()).toUri();
        return created(uri).body(cliente);
    }

    @GetMapping
    public ResponseEntity<Page<ClienteDTO>> list(@PageableDefault(sort = "nome", size = 5) Pageable pageable) {
        return ok(service.list(pageable));
    }
}
