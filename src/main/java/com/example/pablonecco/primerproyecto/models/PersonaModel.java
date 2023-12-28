package com.example.pablonecco.primerproyecto.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PersonaModel {
    private int id;
    private String nombre;
    private String apellido;
    private int dni;
}
