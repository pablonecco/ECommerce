package com.example.pablonecco.primerproyecto.controllers;

import com.example.pablonecco.primerproyecto.helpers.ViewRouteHelper;
import com.example.pablonecco.primerproyecto.services.ICarritoService;
import com.example.pablonecco.primerproyecto.services.implementation.ItemCarritoService;
import com.example.pablonecco.primerproyecto.services.implementation.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/carrito")
public class CarritoController {

    @Autowired
    @Qualifier("carritoService")
    private ICarritoService carritoService;

    @Autowired
    @Qualifier("itemCarritoService")
    private ItemCarritoService itemCarritoService;

    @Autowired
    @Qualifier("productoService")
    private ProductoService productoService;

    @GetMapping("/")
    public ModelAndView carrito () {
        ModelAndView mV = new ModelAndView(ViewRouteHelper.CARRITO);
        mV.addObject("cantidad_carrito", carritoService.calcularItems());
        mV.addObject("items", carritoService.getAll().get(0).getItems());
        mV.addObject("total", carritoService.calcularTotal());
        return mV;
    }

    @PostMapping("/eliminar/{id}")
    public String eliminarDelCarro (@PathVariable("id") int id) {
        int idProducto = itemCarritoService.findById(id).getProducto().getId();
        int cantidad = itemCarritoService.findById(id).getCantidad()*-1;
        itemCarritoService.remove(id);
        productoService.actualizarStock(idProducto, cantidad);
        //return new RedirectView(ViewRouteHelper.R_CARRITO);
        return "redirect:/carrito/";
    }
}
