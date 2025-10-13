package com.example.api.application.service;

import com.example.api.application.port.in.AuthUseCase;
import com.example.api.application.port.out.UserRepositoryPort;
import com.example.api.domain.model.User;
import com.example.api.domain.exception.BusinessException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements AuthUseCase {
    private final UserRepositoryPort users;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public AuthService(UserRepositoryPort users) { this.users = users; }

    @Override
    public User register(String name, String email, String rawPassword) {
        users.findByEmail(email).ifPresent(u -> { throw new BusinessException("Email já em uso"); });
        User u = new User();
        u.setName(name);
        u.setEmail(email);
        u.setPasswordHash(encoder.encode(rawPassword));
        return users.save(u);
    }

    @Override
    public User login(String email, String rawPassword) {
        User u = users.findByEmail(email).orElseThrow(() -> new BusinessException("Credenciais inválidas"));
        if (!encoder.matches(rawPassword, u.getPasswordHash())) throw new BusinessException("Credenciais inválidas");
        return u;
    }
}
