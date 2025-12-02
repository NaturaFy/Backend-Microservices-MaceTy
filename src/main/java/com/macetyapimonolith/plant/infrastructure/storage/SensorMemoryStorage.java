package com.macetyapimonolith.plant.infrastructure.storage;

import com.macetyapimonolith.plant.api.dto.PlantSensorRequest;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SensorMemoryStorage {

    private static final Map<Long, PlantSensorRequest> sensorValues = new ConcurrentHashMap<>();

    public static void updateSensorValue(Long plantId, PlantSensorRequest request) {
        sensorValues.put(plantId, request);
    }

    public static PlantSensorRequest getSensorValue(Long plantId) {
        return sensorValues.get(plantId);
    }
}
