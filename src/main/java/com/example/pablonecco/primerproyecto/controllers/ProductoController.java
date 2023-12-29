package com.example.pablonecco.primerproyecto.controllers;

import ch.qos.logback.core.model.Model;
import com.example.pablonecco.primerproyecto.entities.Producto;
import com.example.pablonecco.primerproyecto.helpers.ViewRouteHelper;
import com.example.pablonecco.primerproyecto.models.PersonaModel;
import com.example.pablonecco.primerproyecto.models.ProductoModel;
import com.example.pablonecco.primerproyecto.services.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequestMapping("/productos")
public class ProductoController {
    @Autowired
    @Qualifier("productoService")
    private IProductoService productoService;

    @GetMapping("/")
    public ModelAndView listarProductos () {
        ModelAndView mV = new ModelAndView(ViewRouteHelper.PRODUCTOS);
        mV.addObject("productos", productoService.getAll());
        mV.addObject("producto", new Producto());
        return mV;
    }

    @GetMapping("/crear")
    public ModelAndView crearProductos () {
        ModelAndView mV = new ModelAndView(ViewRouteHelper.CREAR_PRODUCTOS);
        mV.addObject("productos", productoService.getAll());
        mV.addObject("producto", new Producto());
        return mV;
    }

    @PostMapping("/create")
    public RedirectView create (@ModelAttribute ("producto") ProductoModel productoModel, @RequestParam("file")MultipartFile imagen) {

        if (!imagen.isEmpty()) {
            Path directorioImagenes = Paths.get("src//main//resources//static/images");
            String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();
            try{
                byte[] bytesImg = imagen.getBytes();
                Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + imagen.getOriginalFilename());
                Files.write(rutaCompleta, bytesImg);
                productoModel.setImagen(imagen.getOriginalFilename());
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        productoService.insertOrUpdate(productoModel);
        return new RedirectView(ViewRouteHelper.R_CREAR_PRODUCTOS);
    }

    @PostMapping("/delete/{id}")
    public RedirectView delete (@PathVariable("id") int id) {
        productoService.remove(id);
        return new RedirectView(ViewRouteHelper.R_CREAR_PRODUCTOS);
    }

    @GetMapping("/producto/{id}")
    public ModelAndView verProducto (@PathVariable("id") int id) {
        ModelAndView mV = new ModelAndView(ViewRouteHelper.PRODUCTO);
        mV.addObject("producto", productoService.findById(id));
        return mV;
    }

}
