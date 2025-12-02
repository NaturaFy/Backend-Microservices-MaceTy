package com.macetyapimonolith.plant.api.dto;

public record PlantSensorResponse(
        Long plantId,
        double temp,
        double hum,
        double light,
        String message
) {}
