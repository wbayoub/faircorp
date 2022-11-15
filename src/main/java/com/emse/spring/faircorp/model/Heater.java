package com.emse.spring.faircorp.model;
import javax.persistence.*;

@Entity // (1).
public class Heater {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable=false)
    private String name;

    @Column(nullable=true)
    private Long power;

    @ManyToOne
    @JoinColumn(nullable=false)
    private Room room;

    @Column(nullable=false)
    @Enumerated(EnumType.STRING)
    private HeaterStatus heater_status;

    public Heater() {
    }

    public Heater(String name,Room room,HeaterStatus heater_status) {
        this.name = name;
        this.room = room;
        this.heater_status = heater_status;
    }

    public Long getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPower() {
        return power;
    }

    public void setPower(Long power) {
        this.power = power;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public HeaterStatus getHeater_status() {
        return heater_status;
    }

    public void setHeater_status(HeaterStatus heater_status) {
        this.heater_status = heater_status;
    }
}
