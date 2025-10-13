package com.example.api.api.rest.dto;

import com.example.api.domain.model.Item;

public record ItemResponse(Long id, String name, String sku, Integer quantity) {
    public static ItemResponse of(Item i) {
        return new ItemResponse(i.getId(), i.getName(), i.getSku(), i.getQuantity());
    }
}

