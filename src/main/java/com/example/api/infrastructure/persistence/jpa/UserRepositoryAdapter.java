package com.example.api.infrastructure.persistence.jpa;

import com.example.api.application.port.out.UserRepositoryPort;
import com.example.api.domain.model.User;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public class UserRepositoryAdapter implements UserRepositoryPort {
    private final SpringDataUserRepository jpa;
    public UserRepositoryAdapter(SpringDataUserRepository jpa){ this.jpa = jpa; }
    @Override public User save(User u){ return jpa.save(u); }
    @Override public Optional<User> findByEmail(String email){ return jpa.findByEmail(email); }
    @Override public Optional<User> findById(Long id){ return jpa.findById(id); }
}
