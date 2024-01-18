package com.example.pablonecco.primerproyecto.controllers;

import com.example.pablonecco.primerproyecto.helpers.ViewRouteHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/")
public class HomeController {
    @GetMapping("/")
    public RedirectView home () {
        return new RedirectView(ViewRouteHelper.R_PRODUCTOS);
    }
}
