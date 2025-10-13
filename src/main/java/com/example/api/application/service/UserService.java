package com.example.api.application.service;

import com.example.api.application.port.in.UserUseCase;
import com.example.api.application.port.out.UserRepositoryPort;
import com.example.api.domain.exception.BusinessException;
import com.example.api.domain.model.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserUseCase {

    private final UserRepositoryPort users;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public UserService(UserRepositoryPort users) {
        this.users = users;
    }

    @Override
    public User create(String name, String email, String rawPassword) {
        users.findByEmail(email).ifPresent(u -> { throw new BusinessException("Email já em uso"); });
        User u = new User();
        u.setName(name);
        u.setEmail(email);
        u.setPasswordHash(encoder.encode(rawPassword));
        return users.save(u);
    }

    @Override
    public List<User> listAll() {
        return users.findAll();
    }

    @Override
    public User findById(Long id) {
        return users.findById(id).orElseThrow(() -> new BusinessException("Usuário não encontrado"));
    }
}
