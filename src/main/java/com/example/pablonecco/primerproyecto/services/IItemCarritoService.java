package com.example.pablonecco.primerproyecto.services;

import com.example.pablonecco.primerproyecto.entities.ItemCarrito;
import com.example.pablonecco.primerproyecto.models.ItemCarritoModel;

import java.util.List;

public interface IItemCarritoService {
    public abstract ItemCarritoModel findById (int id);
    public abstract ItemCarritoModel insertOrUpdate (ItemCarritoModel itemCarritoModel);
    public abstract List<ItemCarrito> getAll();
    public abstract boolean remove (int id);
}
