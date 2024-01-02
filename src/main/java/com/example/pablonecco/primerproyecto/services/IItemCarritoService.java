package com.example.pablonecco.primerproyecto.services;

import com.example.pablonecco.primerproyecto.entities.ItemCarrito;

import java.util.List;

public interface IItemCarritoService {
    public abstract ItemCarrito findById (int id);
    public abstract ItemCarrito insertOrUpdate (ItemCarrito itemCarrito);
    public abstract List<ItemCarrito> getAll();
    public abstract boolean remove (int id);
}
