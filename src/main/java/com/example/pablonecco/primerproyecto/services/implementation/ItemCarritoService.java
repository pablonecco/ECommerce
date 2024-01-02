package com.example.pablonecco.primerproyecto.services.implementation;

import com.example.pablonecco.primerproyecto.entities.ItemCarrito;
import com.example.pablonecco.primerproyecto.repositories.IItemCarritoRepository;
import com.example.pablonecco.primerproyecto.services.IItemCarritoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("itemCarritoService")
public class ItemCarritoService implements IItemCarritoService {
    private IItemCarritoRepository itemCarritoRepository;
    public ItemCarrito findById (int id) {
        return itemCarritoRepository.findById(id);
    }

    public ItemCarrito insertOrUpdate (ItemCarrito itemCarrito) {
        itemCarritoRepository.save(itemCarrito);
        return itemCarrito;
    }

    public List<ItemCarrito> getAll () {
        return itemCarritoRepository.findAll();
    }

    public boolean remove (int id) {
        try {
            itemCarritoRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
