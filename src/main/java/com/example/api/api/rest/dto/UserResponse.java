package com.example.api.api.rest.dto;

import com.example.api.domain.model.User;

public record UserResponse(Long id, String name, String email) {
    public static UserResponse of(User u) {
        return new UserResponse(u.getId(), u.getName(), u.getEmail());
    }
}
