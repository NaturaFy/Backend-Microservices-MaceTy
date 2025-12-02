package com.macetyapimonolith.user.application;

import com.macetyapimonolith.user.domain.User;
import com.macetyapimonolith.user.domain.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateUserUseCase {

    private final UserRepository repository;

    public CreateUserUseCase(UserRepository repository) {
        this.repository = repository;
    }

    public User create(User user) {
        return repository.save(user);
    }
}
