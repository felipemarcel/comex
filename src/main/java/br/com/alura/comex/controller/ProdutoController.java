package br.com.alura.comex.controller;

import br.com.alura.comex.dto.ProdutoDTO;
import br.com.alura.comex.model.Produto;
import br.com.alura.comex.service.ProdutoService;
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
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    @GetMapping
    public ResponseEntity<Page<ProdutoDTO>> list(@PageableDefault(sort = "nome", size = 5) Pageable pageable) {
        return ok(service.findAll(pageable).map(ProdutoDTO::from));
    }

    @PostMapping
    public ResponseEntity<Produto> save(@RequestBody @Valid Produto produto, UriComponentsBuilder uriBuilder) {
        Produto produtoSalvo = service.save(produto);
        URI uri = uriBuilder.path("/produtos/{id}").buildAndExpand(produtoSalvo.getId()).toUri();
        return created(uri).body(produtoSalvo);
    }
}
