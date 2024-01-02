package com.example.pablonecco.primerproyecto.models;

import com.example.pablonecco.primerproyecto.entities.Carrito;
import com.example.pablonecco.primerproyecto.entities.Producto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class ItemCarritoModel {
    private int id;
    private Producto producto;
    private Carrito carrito;
    private int cantidad;
}
