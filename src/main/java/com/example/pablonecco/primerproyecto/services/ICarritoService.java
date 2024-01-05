package com.example.pablonecco.primerproyecto.services;

import com.example.pablonecco.primerproyecto.entities.Carrito;
import com.example.pablonecco.primerproyecto.entities.ItemCarrito;
import com.example.pablonecco.primerproyecto.entities.Producto;
import com.example.pablonecco.primerproyecto.models.CarritoModel;
import com.example.pablonecco.primerproyecto.models.ItemCarritoModel;

import java.util.List;

public interface ICarritoService {
    public abstract CarritoModel findById (int id);
    public abstract List<Carrito> getAll();
    public abstract CarritoModel insertOrUpdate (CarritoModel carritoModel);
    public abstract boolean remove (int id);
    public abstract ItemCarritoModel agregarItem (Producto producto, int cantidad);
}
