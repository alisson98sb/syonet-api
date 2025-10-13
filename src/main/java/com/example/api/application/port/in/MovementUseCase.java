package com.example.api.application.port.in;

import com.example.api.domain.model.Movement;
import com.example.api.domain.model.MovementType;
import java.util.List;

public interface MovementUseCase {
    Movement move(Long itemId, MovementType type, Integer amount, Long userId, String note);
    List<Movement> historyByItem(Long itemId);
}
