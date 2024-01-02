package com.example.pablonecco.primerproyecto.models;

import com.example.pablonecco.primerproyecto.entities.Producto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter @NoArgsConstructor
public class CarritoModel {
    public int id;
    public float precioTotal;
}
