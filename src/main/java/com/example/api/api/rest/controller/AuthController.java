package com.example.api.api.rest.controller;

import com.example.api.api.rest.dto.LoginRequest;
import com.example.api.api.rest.dto.RegisterRequest;
import com.example.api.api.rest.dto.UserResponse;
import com.example.api.application.port.in.AuthUseCase;
import com.example.api.application.port.out.UserRepositoryPort;
import com.example.api.domain.model.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthUseCase auth;
    private final UserRepositoryPort users;

    public AuthController(AuthUseCase auth, UserRepositoryPort users) {
        this.auth = auth; this.users = users;
    }

    @PostMapping("/register")
    public UserResponse register(@Valid @RequestBody RegisterRequest req){
        return UserResponse.of(auth.register(req.name(), req.email(), req.password()));
    }

    @PostMapping("/login")
    public UserResponse login(@Valid @RequestBody LoginRequest req, HttpServletRequest http){
        User u = auth.login(req.email(), req.password());
        HttpSession s = http.getSession(true);
        s.setAttribute("USER_ID", u.getId());
        return UserResponse.of(u);
    }

    @PostMapping("/logout")
    public void logout(HttpServletRequest http){
        HttpSession s = http.getSession(false);
        if (s != null) s.invalidate();
    }

    @GetMapping("/me")
    public UserResponse me(HttpServletRequest http){
        HttpSession s = http.getSession(false);
        if (s == null) throw new RuntimeException("Não autenticado");
        Long userId = (Long) s.getAttribute("USER_ID");
        if (userId == null) throw new RuntimeException("Não autenticado");
        User u = users.findById(userId).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        return UserResponse.of(u);
    }
}
