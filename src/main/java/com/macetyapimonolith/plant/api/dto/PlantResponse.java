package com.macetyapimonolith.plant.api.dto;

import com.macetyapimonolith.user.api.dto.UserResponse;

import java.time.LocalDateTime;

public record PlantResponse(
        Long id,
        String name,
        String species,
        String description,
        String imageUrl,
        LocalDateTime createdAt,
        UserResponse user
) {}
