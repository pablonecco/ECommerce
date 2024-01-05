package com.example.pablonecco.primerproyecto.services;

import com.example.pablonecco.primerproyecto.entities.Producto;
import com.example.pablonecco.primerproyecto.models.ProductoModel;

import java.util.List;

public interface IProductoService {
    public abstract ProductoModel findById (int id);
    public abstract List<Producto> getAll();
    public abstract ProductoModel insertOrUpdate (ProductoModel productoModel);
    public abstract boolean remove (int id);
    public abstract void actualizarStock (int id, int cantidad);


}
