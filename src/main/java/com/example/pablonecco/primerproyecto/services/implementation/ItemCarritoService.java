package com.example.pablonecco.primerproyecto.services.implementation;

import com.example.pablonecco.primerproyecto.entities.ItemCarrito;
import com.example.pablonecco.primerproyecto.models.ItemCarritoModel;
import com.example.pablonecco.primerproyecto.repositories.IItemCarritoRepository;
import com.example.pablonecco.primerproyecto.services.IItemCarritoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.List;

@Service("itemCarritoService")
public class ItemCarritoService implements IItemCarritoService {
    @Autowired
    @Qualifier("itemCarritoRepository")
    private IItemCarritoRepository itemCarritoRepository;
    private ModelMapper modelMapper = new ModelMapper();
    public ItemCarritoModel findById (int id) {
        return modelMapper.map(itemCarritoRepository.findById(id), ItemCarritoModel.class);
    }

    public ItemCarritoModel insertOrUpdate (ItemCarritoModel itemCarritoModel) {
        itemCarritoRepository.save(modelMapper.map(itemCarritoModel, ItemCarrito.class));
        return itemCarritoModel;
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

    public void eliminarItems () {
        List<ItemCarrito> items = itemCarritoRepository.findAll();
        for (ItemCarrito item : items) {
            itemCarritoRepository.deleteById(item.getId());
        }
    }
}
