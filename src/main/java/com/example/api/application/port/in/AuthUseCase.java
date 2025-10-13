package com.example.api.application.port.in;

import com.example.api.domain.model.User;

public interface AuthUseCase {
    User register(String name, String email, String rawPassword);
    User login(String email, String rawPassword);
}
