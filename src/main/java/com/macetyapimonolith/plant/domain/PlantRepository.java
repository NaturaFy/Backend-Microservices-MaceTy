package com.macetyapimonolith.plant.domain;

import java.util.List;
import java.util.Optional;

public interface PlantRepository {

    Plant save(Plant plant);

    List<Plant> findByUserId(Long userId);

    Optional<Plant> findById(Long id);
    Optional<Plant> findByDeviceId(String deviceId);

    void delete(Long id);
}
