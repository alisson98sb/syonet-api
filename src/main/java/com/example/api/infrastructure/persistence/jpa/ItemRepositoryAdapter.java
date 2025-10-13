package com.example.api.infrastructure.persistence.jpa;

import com.example.api.application.port.out.ItemRepositoryPort;
import com.example.api.domain.model.Item;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ItemRepositoryAdapter implements ItemRepositoryPort {
    private final SpringDataItemRepository jpa;

    public ItemRepositoryAdapter(SpringDataItemRepository jpa) { this.jpa = jpa; }

    @Override public Item save(Item item) { return jpa.save(item); }
    @Override public List<Item> findAll() { return jpa.findAll(); }
    @Override public Optional<Item> findById(Long id) { return jpa.findById(id); }
}
