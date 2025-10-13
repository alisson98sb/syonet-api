package com.example.api.application.port.out;

import com.example.api.domain.model.Item;
import java.util.List;
import java.util.Optional;

public interface ItemRepositoryPort {
    Item save(Item item);
    List<Item> findAll();
    Optional<Item> findById(Long id);
}
