package com.example.api.infrastructure.persistence.jpa;

import com.example.api.application.port.out.MovementRepositoryPort;
import com.example.api.domain.model.Movement;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MovementRepositoryAdapter implements MovementRepositoryPort {
    private final SpringDataMovementRepository jpa;
    public MovementRepositoryAdapter(SpringDataMovementRepository jpa){ this.jpa = jpa; }

    @Override public Movement save(Movement m){ return jpa.save(m); }
    @Override public List<Movement> findByItemIdDesc(Long itemId){ return jpa.findByItem_IdOrderByCreatedAtDesc(itemId); }
}
