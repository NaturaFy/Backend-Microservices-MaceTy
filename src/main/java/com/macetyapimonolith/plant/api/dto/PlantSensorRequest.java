package com.macetyapimonolith.plant.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record PlantSensorRequest(

        @Schema(description = "Temperatura de la planta", example = "22.5")
        double temp,

        @Schema(description = "Humedad", example = "55.8")
        double hum,

        @Schema(description = "Intensidad lumínica", example = "350.0")
        double light
) {}
