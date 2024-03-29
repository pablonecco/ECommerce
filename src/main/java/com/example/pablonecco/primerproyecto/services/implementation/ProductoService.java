package com.example.pablonecco.primerproyecto.services.implementation;

import com.example.pablonecco.primerproyecto.entities.Producto;
import com.example.pablonecco.primerproyecto.models.ProductoModel;
import com.example.pablonecco.primerproyecto.repositories.IProductoRepository;
import com.example.pablonecco.primerproyecto.services.IProductoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("productoService")
public class ProductoService implements IProductoService {
    @Autowired
    @Qualifier ("productoRepository")
    private IProductoRepository productoRepository;

    private ModelMapper modelMapper = new ModelMapper();

    public Producto findById (int id) {
        return productoRepository.findById(id);
    }

    public List<Producto> getAll () {
        return productoRepository.findAll();
    }

    public ProductoModel insertOrUpdate (ProductoModel productoModel) {
        productoRepository.save(modelMapper.map(productoModel, Producto.class));
        return productoModel;
    }

    public boolean remove (int id) {
        try {
            productoRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void actualizarStock (int id, int cantidad) {
        Producto producto = productoRepository.findById(id);
        producto.setStock(producto.getStock()-cantidad);
        productoRepository.save(producto);
    }

}
