package com.emse.spring.faircorp.model;

import com.emse.spring.faircorp.dto.RoomDto;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.Set;

@Entity // (1).
public class Building {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable=false)
    private String name;
    private Double outsideTemperature;

    public Double getOutsideTemperature() {
        return outsideTemperature;
    }

    public void setOutsideTemperature(Double outsideTemperature) {
        this.outsideTemperature = outsideTemperature;
    }

    public Building(Double outsideTemperature) {
        this.outsideTemperature=outsideTemperature;
    }
    public Building(){
    }

    @OneToMany(mappedBy = "building")
    private Set<Room> rooms;

    public Building(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Room> getRooms(){ return rooms;}

    public void setRooms(Set<Room> rooms){ this.rooms=rooms; }


}