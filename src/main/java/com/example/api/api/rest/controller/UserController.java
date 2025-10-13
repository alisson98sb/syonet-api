package com.example.api.api.rest.controller;

import com.example.api.api.rest.dto.CreateUserRequest;
import com.example.api.api.rest.dto.UserResponse;
import com.example.api.application.port.in.UserUseCase;
import com.example.api.domain.model.User;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserUseCase users;

    public UserController(UserUseCase users) {
        this.users = users;
    }

    @PostMapping
    public UserResponse create(@Valid @RequestBody CreateUserRequest req) {
        User u = users.create(req.name(), req.email(), req.password());
        return UserResponse.of(u);
    }

    @GetMapping
    public List<UserResponse> list() {
        return users.listAll().stream().map(UserResponse::of).toList();
    }

    @GetMapping("/{id}")
    public UserResponse get(@PathVariable Long id) {
        return UserResponse.of(users.findById(id));
    }
}
