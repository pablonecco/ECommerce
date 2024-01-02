package com.example.pablonecco.primerproyecto.repositories;

import com.example.pablonecco.primerproyecto.entities.ItemCarrito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository("itemCarritoRepository")
public interface IItemCarritoRepository extends JpaRepository<ItemCarrito, Serializable> {
    public abstract ItemCarrito findById (int id);
}
