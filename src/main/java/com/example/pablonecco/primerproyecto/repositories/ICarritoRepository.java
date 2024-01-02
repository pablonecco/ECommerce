package com.example.pablonecco.primerproyecto.repositories;

import com.example.pablonecco.primerproyecto.entities.Carrito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository("carritoRepository")
public interface ICarritoRepository extends JpaRepository<Carrito, Serializable> {
    public abstract Carrito findById (int id);
}
