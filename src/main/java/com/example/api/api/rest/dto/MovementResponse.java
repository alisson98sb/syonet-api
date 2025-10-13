package com.example.api.api.rest.dto;

import com.example.api.domain.model.Movement;
import com.example.api.domain.model.MovementType;

import java.time.Instant;

public record MovementResponse(
        Long id, Long itemId, MovementType type, Integer amount,
        Integer balanceAfter, Long userId, String note, Instant createdAt
) {
    public static MovementResponse of(Movement m) {
        Long uid = (m.getUser() == null ? null : m.getUser().getId());
        return new MovementResponse(
                m.getId(),
                m.getItem().getId(),
                m.getType(),
                m.getAmount(),
                m.getBalanceAfter(),
                uid,
                m.getNote(),
                m.getCreatedAt()
        );
    }
}
