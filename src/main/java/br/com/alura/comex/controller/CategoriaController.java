package br.com.alura.comex.controller;

import br.com.alura.comex.dto.CategoriaDTO;
import br.com.alura.comex.model.Categoria;
import br.com.alura.comex.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import static org.springframework.http.ResponseEntity.created;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService service;

    @PostMapping
    public ResponseEntity<CategoriaDTO> save(@RequestBody Categoria categoria, UriComponentsBuilder uriBuilder) {
        Categoria categoriaSalva = service.save(categoria);
        URI uri = uriBuilder.path("/produtos/{id}").buildAndExpand(categoriaSalva.getId()).toUri();
        return created(uri).body(CategoriaDTO.from(categoriaSalva));
    }
}
