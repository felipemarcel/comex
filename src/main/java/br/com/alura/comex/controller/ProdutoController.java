package br.com.alura.comex.controller;

import br.com.alura.comex.model.Produto;
import br.com.alura.comex.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.springframework.http.ResponseEntity.created;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    @GetMapping
    public List<Produto> list() {
        return null;
    }

    @PostMapping
    public ResponseEntity<Produto> save(@RequestBody Produto produto, UriComponentsBuilder uriBuilder) {
        Produto produtoSalvo = service.save(produto);
        URI uri = uriBuilder.path("/produtos/{id}").buildAndExpand(produtoSalvo.getId()).toUri();
        return created(uri).body(produtoSalvo);
    }
}
