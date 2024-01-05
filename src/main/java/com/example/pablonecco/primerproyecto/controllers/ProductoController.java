package com.example.pablonecco.primerproyecto.controllers;

import ch.qos.logback.core.model.Model;
import com.example.pablonecco.primerproyecto.entities.Carrito;
import com.example.pablonecco.primerproyecto.entities.Producto;
import com.example.pablonecco.primerproyecto.helpers.ViewRouteHelper;
import com.example.pablonecco.primerproyecto.models.CarritoModel;
import com.example.pablonecco.primerproyecto.models.PersonaModel;
import com.example.pablonecco.primerproyecto.models.ProductoModel;
import com.example.pablonecco.primerproyecto.services.ICarritoService;
import com.example.pablonecco.primerproyecto.services.IProductoService;
import com.example.pablonecco.primerproyecto.services.implementation.ItemCarritoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
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

    @Autowired
    @Qualifier("carritoService")
    private ICarritoService carritoService;
    @Autowired
    @Qualifier("itemCarritoService")
    private ItemCarritoService itemCarritoService;

    private ModelMapper modelMapper = new ModelMapper();

    @GetMapping("/")
    public ModelAndView listarProductos () {
        ModelAndView mV = new ModelAndView(ViewRouteHelper.PRODUCTOS);
        mV.addObject("productos", productoService.getAll());
        mV.addObject("producto", new Producto());
        mV.addObject("cantidad_carrito", carritoService.calcularItems());
        //itemCarritoService.eliminarItems();
        //System.out.println(carritoService.calcularItems());
       return mV;
    }

    @GetMapping("/crear")
    public ModelAndView crearProductos () {
        ModelAndView mV = new ModelAndView(ViewRouteHelper.CREAR_PRODUCTOS);
        mV.addObject("productos", productoService.getAll());
        mV.addObject("producto", new Producto());
        mV.addObject("cantidad_carrito", carritoService.calcularItems());
        return mV;
    }

    @PostMapping("/create")
    public RedirectView create (@ModelAttribute ("producto") ProductoModel productoModel, @RequestParam("file")MultipartFile imagen) {

        if (!imagen.isEmpty()) {
            String nombreArchivo = (productoModel.getNombre() + System.currentTimeMillis()+imagen.getOriginalFilename());
            Path directorioImagenes = Paths.get("src//main//resources//static/images");
            String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();
            try{
                byte[] bytesImg = imagen.getBytes();
                Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + nombreArchivo);
                Files.write(rutaCompleta, bytesImg);
                productoModel.setImagen(nombreArchivo);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        productoService.insertOrUpdate(productoModel);
        return new RedirectView(ViewRouteHelper.R_CREAR_PRODUCTOS);
    }

    @PostMapping("/delete/{id}")
    public RedirectView delete (@PathVariable("id") int id) {
        Path directorioImagen = Paths.get("src//main//resources//static/images/"+productoService.findById(id).getImagen());
        try{
            Files.delete(directorioImagen);
        } catch (IOException e) {
            e.printStackTrace();
        }
        productoService.remove(id);
        return new RedirectView(ViewRouteHelper.R_CREAR_PRODUCTOS);
    }

    @GetMapping("/producto/{id}")
    public ModelAndView verProducto (@PathVariable("id") int id) {
        ModelAndView mV = new ModelAndView(ViewRouteHelper.PRODUCTO);
        mV.addObject("producto", productoService.findById(id));
        mV.addObject("cantidad_carrito", carritoService.calcularItems());
        return mV;
    }

    @PostMapping("/producto/{id}/alcarro")
    public String alCarro (@PathVariable("id") int id, @RequestParam("cantidad") int cantidad, RedirectAttributes attributes) {
        if (productoService.findById(id).getStock()<cantidad) {
            attributes.addFlashAttribute("error", "Stock insuficiente");
        } else if (cantidad<=0) {
            attributes.addFlashAttribute("error", "No puedes agregar una cantidad menor a 0");
        }else {
            itemCarritoService.insertOrUpdate(carritoService.agregarItem(modelMapper.map(productoService.findById(id), Producto.class), cantidad));
            productoService.actualizarStock(id, cantidad);
            attributes.addFlashAttribute("success", "Se ha hagregado al carrito");
        }
        return "redirect:/productos/producto/"+id;
    }

}
