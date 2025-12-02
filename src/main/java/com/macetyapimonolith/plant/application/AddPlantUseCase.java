package com.macetyapimonolith.plant.application;

import com.macetyapimonolith.plant.api.dto.PlantRequest;
import com.macetyapimonolith.plant.domain.Plant;
import com.macetyapimonolith.plant.domain.PlantRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AddPlantUseCase {

    private final PlantRepository plantRepository;

    public AddPlantUseCase(PlantRepository plantRepository) {
        this.plantRepository = plantRepository;
    }

    public Plant execute(PlantRequest request, Long userId) {

        Plant plant = new Plant(
                null,
                request.getName(),
                request.getSpecies(),
                request.getDescription(),
                request.getImageUrl(),
                LocalDateTime.now(),
                userId,
                null
        );

        return plantRepository.save(plant);
    }


    public Plant update(Plant plant) {
        return plantRepository.save(plant);
    }
}
