package com.example.pablonecco.primerproyecto.controllers;

import com.example.pablonecco.primerproyecto.entities.Persona;
import com.example.pablonecco.primerproyecto.helpers.ViewRouteHelper;
import com.example.pablonecco.primerproyecto.models.PersonaModel;
import com.example.pablonecco.primerproyecto.services.IPersonaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/personas")
public class PersonaController {
    @Autowired
    @Qualifier("personaService")
    private IPersonaService personaService;

    private ModelMapper modelMapper;

    @GetMapping("/")
    public ModelAndView index () {
        ModelAndView mV = new ModelAndView(ViewRouteHelper.PERSONAS);
        mV.addObject("personas", personaService.getAll());
        mV.addObject("persona", new Persona());
        return mV;
    }

    @PostMapping("/create")
    public RedirectView create (@ModelAttribute("persona") PersonaModel personaModel) {
        personaService.insertOrUpdate(personaModel);
        return new RedirectView(ViewRouteHelper.R_PERSONA);
    }

    @GetMapping("/editar/{id}")
    public ModelAndView edit (@PathVariable("id") int id){
        ModelAndView mV = new ModelAndView(ViewRouteHelper.PERSONA_EDITAR);
        mV.addObject("persona", personaService.findById(id));
        return mV;
    }

    @PostMapping("/guardar/{id}")
    public RedirectView save (@ModelAttribute ("persona") PersonaModel persona) {
        personaService.insertOrUpdate(persona);
        return new RedirectView(ViewRouteHelper.R_PERSONA);
    }

    @PostMapping("/eliminar/{id}")
    public RedirectView delete(@PathVariable("id") int id){
        personaService.remove(id);
        return new RedirectView(ViewRouteHelper.R_PERSONA);
    }

}
