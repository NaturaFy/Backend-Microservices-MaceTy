package com.macetyapimonolith.plant.domain;

import java.time.LocalDateTime;

public class Plant {

    private Long id;
    private String name;
    private String species;
    private String description;
    private String imageUrl;
    private LocalDateTime createdAt;
    private Long userId;
    private String deviceId; // ⬅️ agregado

    public Plant(Long id, String name, String species, String description,
                 String imageUrl, LocalDateTime createdAt, Long userId, String deviceId) {
        this.id = id;
        this.name = name;
        this.species = species;
        this.description = description;
        this.imageUrl = imageUrl;
        this.createdAt = createdAt;
        this.userId = userId;
        this.deviceId = deviceId;
    }

    // Getters
    public Long getId() { return id; }
    public String getName() { return name; }
    public String getSpecies() { return species; }
    public String getDescription() { return description; }
    public String getImageUrl() { return imageUrl; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public Long getUserId() { return userId; }
    public String getDeviceId() { return deviceId; }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
}
