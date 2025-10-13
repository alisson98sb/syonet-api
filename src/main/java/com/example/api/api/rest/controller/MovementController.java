package com.example.api.api.rest.controller;

import com.example.api.api.rest.dto.MovementRequest;
import com.example.api.api.rest.dto.MovementResponse;
import com.example.api.application.port.in.MovementUseCase;
import com.example.api.domain.model.Movement;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movements")
public class MovementController {

    private final MovementUseCase useCase;

    public MovementController(MovementUseCase useCase) { this.useCase = useCase; }

    // cria movimentação (usa usuário da sessão, se houver)
    @PostMapping
    public MovementResponse move(@Valid @RequestBody MovementRequest req, HttpServletRequest http) {
        Long userId = null;
        HttpSession s = http.getSession(false);
        if (s != null && s.getAttribute("USER_ID") != null) {
            userId = (Long) s.getAttribute("USER_ID");
        }
        Movement m = useCase.move(req.itemId(), req.type(), req.amount(), userId, req.note());
        return MovementResponse.of(m);
    }

    // histórico por item
    @GetMapping
    public List<MovementResponse> historyByItem(@RequestParam Long itemId) {
        return useCase.historyByItem(itemId).stream().map(MovementResponse::of).toList();
    }
}
