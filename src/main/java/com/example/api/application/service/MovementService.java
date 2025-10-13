package com.example.api.application.service;

import com.example.api.application.port.in.MovementUseCase;
import com.example.api.application.port.out.ItemRepositoryPort;
import com.example.api.application.port.out.MovementRepositoryPort;
import com.example.api.application.port.out.UserRepositoryPort;
import com.example.api.domain.exception.BusinessException;
import com.example.api.domain.model.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MovementService implements MovementUseCase {

    private final ItemRepositoryPort items;
    private final MovementRepositoryPort moves;
    private final UserRepositoryPort users;

    public MovementService(ItemRepositoryPort items, MovementRepositoryPort moves, UserRepositoryPort users) {
        this.items = items; this.moves = moves; this.users = users;
    }

    @Override
    @Transactional
    public Movement move(Long itemId, MovementType type, Integer amount, Long userId, String note) {
        if (amount == null || amount <= 0) throw new BusinessException("Quantidade inválida");

        Item item = items.findById(itemId).orElseThrow(() -> new BusinessException("Item não encontrado"));
        int current = item.getQuantity() == null ? 0 : item.getQuantity();
        int delta = (type == MovementType.IN ? amount : -amount);
        int next = current + delta;
        if (next < 0) throw new BusinessException("Estoque insuficiente");

        item.setQuantity(next);
        items.save(item);

        Movement m = new Movement();
        m.setItem(item);
        m.setType(type);
        m.setAmount(amount);
        m.setBalanceAfter(next);
        if (userId != null) users.findById(userId).ifPresent(m::setUser);
        m.setNote(note);

        return moves.save(m);
    }

    @Override
    public List<Movement> historyByItem(Long itemId) {
        return moves.findByItemIdDesc(itemId);
    }
}
