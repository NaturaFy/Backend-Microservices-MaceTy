package com.macetyapimonolith.plant.domain.model;

public class Sensor {
    private String deviceId;
    private Long plantId;

    public Sensor(String deviceId, Long plantId) {
        this.deviceId = deviceId;
        this.plantId = plantId;
    }

    public String getDeviceId() { return deviceId; }
    public Long getPlantId() { return plantId; }
}
