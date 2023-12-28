package com.example.pablonecco.primerproyecto.repositories;

import com.example.pablonecco.primerproyecto.entities.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository("personaRepository")
public interface IPersonaRepository extends JpaRepository<Persona, Serializable> {
    public abstract Persona findById(int id);
    public abstract Persona findByNombre(String nombre);
}
