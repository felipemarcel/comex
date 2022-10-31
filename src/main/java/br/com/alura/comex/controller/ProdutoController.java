package br.com.alura.comex.controller;

import br.com.alura.comex.dto.ProdutoDTO;
import br.com.alura.comex.model.Produto;
import br.com.alura.comex.service.ProdutoService;
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

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDTO> find(@PathVariable @Min(1) Long id) {
        return ok(ProdutoDTO.from(service.findBy(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoDTO> update(@RequestBody @Valid Produto produto, @PathVariable("id") @Min(1) Long id) {
        return ok(ProdutoDTO.from(service.update(produto, id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") @Min(1) Long id) {
        service.delete(id);
        return ok().build();
    }
}
