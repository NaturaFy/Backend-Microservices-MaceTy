package com.macetyapimonolith.plant.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SpringPlantRepository extends JpaRepository<PlantEntity, Long> {
    List<PlantEntity> findByUserId(Long userId);
    Optional<PlantEntity> findByDeviceId(String deviceId);

}
