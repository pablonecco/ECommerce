package com.example.pablonecco.primerproyecto.repositories;

import com.example.pablonecco.primerproyecto.entities.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository("productoRepository")
public interface IProductoRepository extends JpaRepository<Producto, Serializable> {
    public abstract Producto findById (int id);
    public abstract Producto findByNombre(String nombre);
}
