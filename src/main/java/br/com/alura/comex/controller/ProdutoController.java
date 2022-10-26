package br.com.alura.comex.controller;

import br.com.alura.comex.model.Produto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/produtos")
public class ProdutoController {

    @GetMapping
    public List<Produto> list() {
        return null;
    }
}
