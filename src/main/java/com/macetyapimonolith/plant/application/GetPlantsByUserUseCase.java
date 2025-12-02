package com.macetyapimonolith.plant.application;

import com.macetyapimonolith.plant.domain.Plant;
import com.macetyapimonolith.plant.domain.PlantRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GetPlantsByUserUseCase {

    private final PlantRepository plantRepository;

    public GetPlantsByUserUseCase(PlantRepository plantRepository) {
        this.plantRepository = plantRepository;
    }

    public List<Plant> execute(Long userId) {
        return plantRepository.findByUserId(userId);
    }

    public Optional<Plant> sensor(String sensorId) {
        return plantRepository.findByDeviceId(sensorId);
    }
}
