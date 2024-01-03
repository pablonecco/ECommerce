package com.example.pablonecco.primerproyecto.services;

import com.example.pablonecco.primerproyecto.entities.Carrito;
import com.example.pablonecco.primerproyecto.models.CarritoModel;

import java.util.List;

public interface ICarritoService {
    public abstract CarritoModel findById (int id);
    public abstract List<Carrito> getAll();
    public abstract CarritoModel insertOrUpdate (CarritoModel carritoModel);
    public abstract boolean remove (int id);
}
