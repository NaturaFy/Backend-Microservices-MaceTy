package com.macetyapimonolith.plant.infrastructure;

import com.macetyapimonolith.plant.domain.Plant;
import com.macetyapimonolith.plant.domain.PlantRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PlantRepositoryAdapter implements PlantRepository {

    private final SpringPlantRepository repo;

    public PlantRepositoryAdapter(SpringPlantRepository repo) {
        this.repo = repo;
    }

    @Override
    public Plant save(Plant plant) {
        PlantEntity entity = PlantMapper.toEntity(plant);
        PlantEntity saved = repo.save(entity);
        return PlantMapper.toDomain(saved);
    }

    @Override
    public List<Plant> findByUserId(Long userId) {
        return repo.findByUserId(userId)
                .stream()
                .map(PlantMapper::toDomain)
                .toList();
    }

    @Override
    public Optional<Plant> findById(Long id) {
        return repo.findById(id).map(PlantMapper::toDomain);
    }

    @Override
    public Optional<Plant> findByDeviceId(String deviceId) {
        return repo.findByDeviceId(deviceId).map(PlantMapper::toDomain);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }
}

