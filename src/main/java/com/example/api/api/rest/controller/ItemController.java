package com.example.api.api.rest.controller;

import com.example.api.api.rest.dto.CreateItemRequest;
import com.example.api.api.rest.dto.ItemResponse;
import com.example.api.application.port.in.ItemUseCase;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {

    private final ItemUseCase useCase;

    public ItemController(ItemUseCase useCase) { this.useCase = useCase; }

    @PostMapping
    public ItemResponse create(@RequestBody CreateItemRequest req) {
        return ItemResponse.of(useCase.create(req.name(), req.sku()));
    }

    @GetMapping
    public List<ItemResponse> list() {
        return useCase.listAll().stream().map(ItemResponse::of).toList();
    }
}

