package com.example.pablonecco.primerproyecto.controllers;

import com.example.pablonecco.primerproyecto.services.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/productos")
public class ProductoController {
    @Autowired
    @Qualifier("productoService")
    private IProductoService productoService;


}
