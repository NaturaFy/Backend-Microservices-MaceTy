package com.macetyapimonolith.plant.infrastructure;

import com.macetyapimonolith.plant.domain.Plant;
import com.macetyapimonolith.user.domain.User;

public class PlantMapper {

    public static Plant toDomain(PlantEntity entity) {
        return new Plant(
                entity.getId(),
                entity.getName(),
                entity.getSpecies(),
                entity.getDescription(),
                entity.getImageUrl(),
                entity.getCreatedAt(),
                entity.getUser().getId(),
                entity.getDeviceId()
        );
    }

    public static PlantEntity toEntity(Plant domain) {
        PlantEntity entity = new PlantEntity();
        entity.setId(domain.getId());
        entity.setName(domain.getName());
        entity.setSpecies(domain.getSpecies());
        entity.setDescription(domain.getDescription());
        entity.setImageUrl(domain.getImageUrl());
        entity.setCreatedAt(domain.getCreatedAt());
        entity.setDeviceId(domain.getDeviceId());

        User user = new User();
        user.setId(domain.getUserId());
        entity.setUser(user);

        return entity;
    }
}
