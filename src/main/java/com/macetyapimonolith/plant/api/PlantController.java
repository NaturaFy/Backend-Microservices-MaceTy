package com.macetyapimonolith.plant.api;

import com.macetyapimonolith.plant.api.dto.*;
import com.macetyapimonolith.user.api.dto.UserResponse;
import com.macetyapimonolith.plant.application.AddPlantUseCase;
import com.macetyapimonolith.plant.application.GetPlantsByUserUseCase;
import com.macetyapimonolith.plant.domain.Plant;
import com.macetyapimonolith.user.domain.User;
import com.macetyapimonolith.plant.infrastructure.storage.SensorMemoryStorage;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/plants")
public class PlantController {

    private final AddPlantUseCase addPlantUseCase;
    private final GetPlantsByUserUseCase getPlantsByUserUseCase;

    public PlantController(AddPlantUseCase addPlantUseCase, GetPlantsByUserUseCase getPlantsByUserUseCase) {
        this.addPlantUseCase = addPlantUseCase;
        this.getPlantsByUserUseCase = getPlantsByUserUseCase;
    }

    @PostMapping
    public ResponseEntity<?> addPlant(@RequestBody PlantRequest request, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        Plant createdPlant = addPlantUseCase.execute(request, user.getId());

        return ResponseEntity.ok(new PlantResponse(
                createdPlant.getId(),
                createdPlant.getName(),
                createdPlant.getSpecies(),
                createdPlant.getDescription(),
                createdPlant.getImageUrl(),
                createdPlant.getCreatedAt(),
                new UserResponse(user.getId(), user.getName(), user.getEmail(), user.getAvatar(), user.getLocation(), user.getCreatedAt())
        ));
    }

    @GetMapping
    public ResponseEntity<List<PlantResponse>> getMyPlants(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        List<Plant> plants = getPlantsByUserUseCase.execute(user.getId());

        List<PlantResponse> response = plants.stream()
                .map(plant -> new PlantResponse(
                        plant.getId(),
                        plant.getName(),
                        plant.getSpecies(),
                        plant.getDescription(),
                        plant.getImageUrl(),
                        plant.getCreatedAt(),
                        new UserResponse(user.getId(), user.getName(), user.getEmail(), user.getAvatar(), user.getLocation(), user.getCreatedAt())
                )).toList();

        return ResponseEntity.ok(response);
    }

    // 👇 Nuevo endpoint para vincular sensor a planta
    @PostMapping("/{plantId}/sensor/register")
    public ResponseEntity<?> registerSensor(
            @PathVariable Long plantId,
            @RequestBody AssignSensorRequest request,
            Authentication authentication
    ) {
        User user = (User) authentication.getPrincipal();

        Optional<Plant> plantOpt = getPlantsByUserUseCase
                .execute(user.getId())
                .stream()
                .filter(p -> p.getId().equals(plantId))
                .findFirst();

        if (plantOpt.isEmpty()) {
            return ResponseEntity.status(403).body("No tienes permiso para modificar esta planta");
        }

        Plant plant = plantOpt.get();
        plant.setDeviceId(request.deviceId());
        addPlantUseCase.update(plant);

        return ResponseEntity.ok("Sensor registrado exitosamente");
    }


    @PostMapping("/sensor/{deviceId}")
    public ResponseEntity<?> receiveSensorDataFromDevice(
            @PathVariable String deviceId,
            @RequestBody PlantSensorRequest sensorRequest
    ) {
        //User user = (User) authentication.getPrincipal();

        //Optional<Plant> plantOpt = getPlantsByUserUseCase.execute(user.getId()).stream().filter(plant -> deviceId.equals(plant.getDeviceId())).findFirst();
        Optional<Plant> plantOpt = getPlantsByUserUseCase.sensor(deviceId);

        if (plantOpt.isEmpty()) {
            System.out.println("‼️Sensor aun no registrado datos omitidos");
            return ResponseEntity.status(200).body("Recibido");
        }

        Plant plant = plantOpt.get();

        SensorMemoryStorage.updateSensorValue(plant.getId(), sensorRequest);

        return ResponseEntity.ok(new PlantSensorResponse(
                plant.getId(),
                sensorRequest.temp(),
                sensorRequest.hum(),
                sensorRequest.light(),
                "Datos recibidos correctamente del sensor " + deviceId
        ));
    }


    @GetMapping("/{plantId}/sensor/latest")
    public ResponseEntity<?> getLatestSensorData(
            @PathVariable Long plantId,
            Authentication authentication
    ) {
        User user = (User) authentication.getPrincipal();

        boolean plantExists = getPlantsByUserUseCase
                .execute(user.getId())
                .stream()
                .anyMatch(p -> p.getId().equals(plantId));

        if (!plantExists) {
            return ResponseEntity.status(403).body("No tienes acceso a esta planta");
        }

        var data = SensorMemoryStorage.getSensorValue(plantId);

        if (data == null) {
            return ResponseEntity.status(404).body("No hay datos aún");
        }

        return ResponseEntity.ok(data);
    }
}
