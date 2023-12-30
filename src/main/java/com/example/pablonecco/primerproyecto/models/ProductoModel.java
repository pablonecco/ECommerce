package com.example.pablonecco.primerproyecto.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class ProductoModel {
    private int id;
    private String nombre;
    private int stock;
    private float precio;
    private String imagen;
    private String descripcion;
}
