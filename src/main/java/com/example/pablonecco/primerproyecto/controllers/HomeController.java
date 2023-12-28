package com.example.pablonecco.primerproyecto.controllers;

import com.example.pablonecco.primerproyecto.helpers.ViewRouteHelper;
import com.example.pablonecco.primerproyecto.models.PersonaModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class HomeController {
    @GetMapping("/registro")
    public String registro(Model model){
        model.addAttribute("persona", new PersonaModel());
        return ViewRouteHelper.REGISTRO;
    }

    @PostMapping("/mostrarpersona")
    public ModelAndView mostrarpersona(@ModelAttribute("personaModel") PersonaModel personaModel) {
        ModelAndView mV = new ModelAndView();
        mV.setViewName(ViewRouteHelper.PERSONA);
        mV.addObject("persona", personaModel);
        return mV;
    }
}
