package com.example.pablonecco.primerproyecto.services;

import com.example.pablonecco.primerproyecto.entities.Producto;
import com.example.pablonecco.primerproyecto.models.ProductoModel;

import java.util.List;

public interface IProductoService {
    public ProductoModel findById (int id);
    public List<Producto> getAll();
    public ProductoModel insertOrUpdate (ProductoModel productoModel);
    public boolean remove (int id);


}
