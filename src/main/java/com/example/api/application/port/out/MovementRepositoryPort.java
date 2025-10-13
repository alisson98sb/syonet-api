package com.example.api.application.port.out;

import com.example.api.domain.model.Movement;
import java.util.List;

public interface MovementRepositoryPort {
    Movement save(Movement m);
    List<Movement> findByItemIdDesc(Long itemId);
}
