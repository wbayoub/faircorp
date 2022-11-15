package com.emse.spring.faircorp.model;

import com.emse.spring.faircorp.dto.HeaterDto;
import com.emse.spring.faircorp.dto.WindowDto;

import javax.persistence.*;
import java.util.Set;
import java.util.stream.Collectors;

@Entity // (1).
public class Room {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable=false)
    private Integer floor;

    @Column(nullable=false)
    private String name;

    private Double current_temperature;

    private Double target_temperature;

    @OneToMany(mappedBy = "room")
    private Set<Heater> heaters;

    @OneToMany(mappedBy = "room")
    private Set<Window> windows;

    @ManyToOne
    @JoinColumn(nullable=false)
    private Building building;

    public void setId(Long id) {
        this.id = id;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public Building getBuilding() {
        return building;
    }

    public Long getId() {
        return id;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getCurrent_temperature() {
        return current_temperature;
    }

    public void setCurrent_temperature(Double current_temperature) {
        current_temperature = current_temperature;
    }

    public Double getTarget_temperature() {
        return target_temperature;
    }

    public void setTarget_temperature(Double target_temperature) {
        target_temperature = target_temperature;
    }

    public Set<Heater> getHeaters() {
        return heaters;
    }

    public void setHeaters(Set<Heater> heaters) {
        this.heaters = heaters;
    }

    public Set<Window> getWindows() {
        return windows;
    }

    public void setWindows(Set<Window> windows) {
        this.windows = windows;
    }

    public Room(){
    }
    public Room(Integer floor,String name){
        this.floor=floor;
        this.name=name;
    }
    public Room(Integer floor,String name,Building building, Double current_temperature ,Double target_temperature){
        this.floor=floor;
        this.name=name;
        this.building=building;
        this.current_temperature=current_temperature;
        this.target_temperature=target_temperature;
    }
}
