package br.com.alura.comex.controller;

import br.com.alura.comex.dto.CategoriaDTO;
import br.com.alura.comex.exception.NotFoundException;
import br.com.alura.comex.model.Categoria;
import br.com.alura.comex.service.CategoriaService;
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
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.net.URI;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.ResponseEntity.created;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService service;

    @PostMapping
    public ResponseEntity<CategoriaDTO> save(@RequestBody @Valid Categoria categoria, UriComponentsBuilder uriBuilder) {
        Categoria categoriaSalva = service.save(categoria);
        URI uri = uriBuilder.path("/categoria/{id}").buildAndExpand(categoriaSalva.getId()).toUri();
        return created(uri).body(CategoriaDTO.from(categoriaSalva));
    }

    @GetMapping
    public ResponseEntity<Page<CategoriaDTO>> list(@PageableDefault(sort = "nome", size = 5) Pageable pageable) {
        return ok().body(service.findAll(pageable).map(CategoriaDTO::from));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDTO> findById(@PathVariable("id") @Min(1) Long id) {
        Categoria categoria = service.findById(id);
        return ok().body(CategoriaDTO.from(categoria));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDTO> update(@PathVariable("id") @Min(1) Long id, @RequestBody Categoria categoria) {
        return ok().body(CategoriaDTO.from(service.update(categoria, id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") @Min(1) Long id) {
        service.delete(id);
        return ok().build();
    }
}
