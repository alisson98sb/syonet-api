package com.example.api.infrastructure.persistence.jpa;

import com.example.api.domain.model.Movement;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SpringDataMovementRepository extends JpaRepository<Movement, Long> {
    List<Movement> findByItem_IdOrderByCreatedAtDesc(Long itemId);
}
