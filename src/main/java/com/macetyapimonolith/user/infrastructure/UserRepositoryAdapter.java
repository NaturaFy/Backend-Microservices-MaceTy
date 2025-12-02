package com.macetyapimonolith.user.infrastructure;

import com.macetyapimonolith.user.domain.*;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepositoryAdapter implements UserRepository {

    private final SpringUserJpaRepository repo;

    public UserRepositoryAdapter(SpringUserJpaRepository repo) {
        this.repo = repo;
    }

    @Override
    public User save(User user) {
        return repo.save(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return repo.findByEmail(email);
    }

    @Override
    public Optional<User> findById(Long id) {
        return repo.findById(id);
    }

    @Override
    public boolean existsByEmail(String email) {
        return repo.existsByEmail(email);
    }
    @Override
    public void deleteById(Long id) {
        repo.deleteById(id);
    }
}
