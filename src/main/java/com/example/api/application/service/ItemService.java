package com.example.api.application.service;

import com.example.api.application.port.in.ItemUseCase;
import com.example.api.application.port.out.ItemRepositoryPort;
import com.example.api.domain.model.Item;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService implements ItemUseCase {

    private final ItemRepositoryPort repo;

    public ItemService(ItemRepositoryPort repo) { this.repo = repo; }

    @Override
    public Item create(String name, String sku) {
        Item i = new Item();
        i.setName(name);
        i.setSku(sku);
        i.setQuantity(0);
        return repo.save(i);
    }

    @Override
    public List<Item> listAll() {
        return repo.findAll();
    }
}

