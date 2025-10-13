package com.example.api.api.rest.dto;

import com.example.api.domain.model.MovementType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record MovementRequest(
        @NotNull Long itemId,
        @NotNull MovementType type,
        @NotNull @Min(1) Integer amount,
        String note
) {}
