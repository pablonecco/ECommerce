package com.example.pablonecco.primerproyecto.services;

import com.example.pablonecco.primerproyecto.entities.Persona;
import com.example.pablonecco.primerproyecto.models.PersonaModel;

import java.util.List;

public interface IPersonaService {
    public PersonaModel findById (int id);
    public List<Persona> getAll();
    public PersonaModel insertOrUpdate(PersonaModel personaModel);
    public boolean remove(int id);
}
