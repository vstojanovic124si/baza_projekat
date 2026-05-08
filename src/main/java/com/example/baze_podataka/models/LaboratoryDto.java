package com.example.baze_podataka.models;

public class LaboratoryDto {
    private int laboratoryId;
    private String laboratoryName;
    private String location;

    public LaboratoryDto(int laboratoryId, String laboratoryName, String location) {
        this.laboratoryId = laboratoryId;
        this.laboratoryName = laboratoryName;
        this.location = location;
    }

    public int getLaboratoryId() {
        return laboratoryId;
    }

    public void setLaboratoryId(int laboratoryId) {
        this.laboratoryId = laboratoryId;
    }

    public String getLaboratoryName() {
        return laboratoryName;
    }

    public void setLaboratoryName(String laboratoryName) {
        this.laboratoryName = laboratoryName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
