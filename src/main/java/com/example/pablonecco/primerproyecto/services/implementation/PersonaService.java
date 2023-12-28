package com.example.pablonecco.primerproyecto.services.implementation;

import com.example.pablonecco.primerproyecto.entities.Persona;
import com.example.pablonecco.primerproyecto.models.PersonaModel;
import com.example.pablonecco.primerproyecto.repositories.IPersonaRepository;
import com.example.pablonecco.primerproyecto.services.IPersonaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("personaService")
public class PersonaService implements IPersonaService {
    @Autowired
    @Qualifier("personaRepository")
    private IPersonaRepository personaRepository;

    private ModelMapper modelMapper = new ModelMapper();

    public PersonaModel findById (int id) {
        return modelMapper.map(personaRepository.findById(id), PersonaModel.class);
    }

    @Override
    public List<Persona> getAll(){
        return personaRepository.findAll();
    }

    @Override
    public PersonaModel insertOrUpdate(PersonaModel personaModel){
        Persona persona = personaRepository.save(modelMapper.map(personaModel, Persona.class));
        return modelMapper.map(persona, PersonaModel.class);
    }

    public boolean remove (int id) {
        try {
            personaRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
