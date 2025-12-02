package com.macetyapimonolith.user.api;

import com.macetyapimonolith.user.domain.User;
import com.macetyapimonolith.plant.infrastructure.PlantJpaRepository;
import com.macetyapimonolith.user.domain.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/profile")
public class ProfileController {

    private final UserRepository userRepository;
    private final PlantJpaRepository plantJpaRepository;

    public ProfileController(UserRepository userRepository, PlantJpaRepository plantJpaRepository) {
        this.userRepository = userRepository;
        this.plantJpaRepository = plantJpaRepository;
    }

    @GetMapping
    public ResponseEntity<?> getProfile(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        user.setPassword(null);
        return ResponseEntity.ok(user);
    }

    @PutMapping
    public ResponseEntity<?> updateProfile(Authentication authentication,
                                           @RequestBody Map<String, Object> updates) {
        User user = (User) authentication.getPrincipal();

        if (updates.containsKey("name")) {
            user.setName((String) updates.get("name"));
        }
        if (updates.containsKey("avatar")) {
            user.setAvatar((String) updates.get("avatar"));
        }
        if (updates.containsKey("location")) {
            user.setLocation((String) updates.get("location"));
        }

        userRepository.save(user);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Perfil actualizado correctamente");
        response.put("user", user);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteProfile(Authentication authentication) {
        User user = (User) authentication.getPrincipal();

        plantJpaRepository.deleteByUser(user);

        userRepository.deleteById(user.getId());

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Cuenta y plantas eliminadas correctamente");

        return ResponseEntity.ok(response);
    }
}
