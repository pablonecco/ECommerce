package com.example.pablonecco.primerproyecto.services;

import com.example.pablonecco.primerproyecto.models.CarritoModel;

public interface ICarritoService {
    public abstract CarritoModel findById (int id);
    public abstract CarritoModel insertOrUpdate (CarritoModel carritoModel);
    public abstract boolean remove (int id);
}
