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

    public ProductoModel findById (int id) {
        return modelMapper.map(productoRepository.findById(id), ProductoModel.class);
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
}
