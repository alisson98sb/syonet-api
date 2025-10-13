package com.example.api.application.port.in;

import com.example.api.domain.model.Item;
import java.util.List;

public interface ItemUseCase {
    Item create(String name, String sku);
    List<Item> listAll();
}
