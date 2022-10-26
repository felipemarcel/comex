package br.com.alura.comex.controller;

import br.com.alura.comex.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService service;



}
