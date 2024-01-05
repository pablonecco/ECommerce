package com.example.pablonecco.primerproyecto.services.implementation;

import com.example.pablonecco.primerproyecto.entities.Carrito;
import com.example.pablonecco.primerproyecto.entities.ItemCarrito;
import com.example.pablonecco.primerproyecto.entities.Producto;
import com.example.pablonecco.primerproyecto.models.CarritoModel;
import com.example.pablonecco.primerproyecto.models.ItemCarritoModel;
import com.example.pablonecco.primerproyecto.repositories.ICarritoRepository;
import com.example.pablonecco.primerproyecto.services.ICarritoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("carritoService")
public class CarritoService implements ICarritoService {
    @Autowired
    @Qualifier("carritoRepository")
    private ICarritoRepository carritoRepository;

    private ModelMapper modelMapper = new ModelMapper();

    public CarritoModel findById (int id) {
        return modelMapper.map(carritoRepository.findById(id), CarritoModel.class);
    }

    public List<Carrito> getAll () {
        return carritoRepository.findAll();
    }

    public CarritoModel insertOrUpdate (CarritoModel carritoModel) {
        carritoRepository.save(modelMapper.map(carritoModel, Carrito.class));
        return carritoModel;
    }

    public boolean remove (int id) {
        try {
            carritoRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public ItemCarritoModel agregarItem (Producto producto, int cantidad) {
        if (carritoRepository.findAll().isEmpty()) {
            Carrito carrito = new Carrito();
            carritoRepository.save(carrito);
        }
        Carrito carrito = carritoRepository.findAll().get(0);

        for (ItemCarrito item : carrito.getItems()) {
            if (item.getProducto().getId()== producto.getId()) {
                item.setCantidad(item.getCantidad()+cantidad);
                return modelMapper.map(item, ItemCarritoModel.class);
            }
        }

        ItemCarritoModel itemCarrito = new ItemCarritoModel ();
        itemCarrito.setCarrito(carrito);
        itemCarrito.setCantidad(cantidad);
        itemCarrito.setProducto(producto);
        return itemCarrito;
    }

    public int calcularItems () {
        Carrito carrito = carritoRepository.findAll().get(0);
        int cantidad = 0;
        for (ItemCarrito item : carrito.getItems()) {
            cantidad = cantidad + item.getCantidad();
        }
        return cantidad;
    }

    public float calcularTotal () {
        float total = 0;
        Carrito carrito = carritoRepository.findAll().get(0);
        for (ItemCarrito item : carrito.getItems()) {
            total = total + item.getProducto().getPrecio()*item.getCantidad();
        }
        return total;
    }
}
