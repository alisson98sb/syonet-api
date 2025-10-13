package com.example.api.application.port.in;

import com.example.api.domain.model.User;
import java.util.List;

public interface UserUseCase {
    User create(String name, String email, String rawPassword);
    List<User> listAll();
    User findById(Long id);
}
