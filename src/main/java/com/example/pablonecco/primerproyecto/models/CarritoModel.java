package com.example.pablonecco.primerproyecto.models;

import com.example.pablonecco.primerproyecto.entities.ItemCarrito;
import com.example.pablonecco.primerproyecto.entities.Producto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter @NoArgsConstructor
public class CarritoModel {
    private int id;
    private float precioTotal;
    private Set<ItemCarrito> items = new HashSet<>();
}
