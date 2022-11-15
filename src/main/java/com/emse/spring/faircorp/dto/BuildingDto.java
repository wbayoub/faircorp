package com.emse.spring.faircorp.dto;

import com.emse.spring.faircorp.model.Building;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class BuildingDto {

    private Long id;

    private Double outsideTemperature;

    public Double getOutsideTemperature() {
        return outsideTemperature;
    }

    public void setOutsideTemperature(Double outsideTemperature) {
        this.outsideTemperature = outsideTemperature;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BuildingDto(){
    }
    public BuildingDto(Building building){
        this.id=building.getId();
        this.outsideTemperature=building.getOutsideTemperature();
    }
}
