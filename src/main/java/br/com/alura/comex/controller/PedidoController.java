package br.com.alura.comex.controller;

import br.com.alura.comex.model.Pedido;
import br.com.alura.comex.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.net.URI;

import static org.springframework.http.ResponseEntity.created;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService service;

    @GetMapping
    public ResponseEntity<Page<Pedido>> findAll(@PageableDefault(sort = "nome", size = 5) Pageable pageable) {
        return ok().body(service.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> findById(@PathVariable("id") @Min(1) Long id) {
        return ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<Pedido> save(@RequestBody @Valid Pedido pedido, UriComponentsBuilder uriBuilder) {
        Pedido pedidoSalvo = service.save(pedido);
        URI uri = uriBuilder.path("/pedidos/{id}").buildAndExpand(pedidoSalvo.getId()).toUri();
        return created(uri).body(pedidoSalvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pedido> update(@RequestBody @Valid Pedido pedido, @Min(1) Long id) {
        return ok(service.update(pedido, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") @Min(1) Long id) {
        service.delete(id);
        return ok().build();
    }
}
