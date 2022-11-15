package com.emse.spring.faircorp.model;
import javax.persistence.*;

@Entity // (1).
@Table(name = "RWINDOW") // (2).
public class Window {
    @Id // (3).
    @GeneratedValue
    private Long id;
    @ManyToOne
    private Room room;
    @Column(nullable=false)
    private String name;

    @Column(nullable=false)
    @Enumerated(EnumType.STRING)
    private WindowStatus windowStatus;

    public Window() {
    }

    public Window(String name) {
        this.name = name;
    }

    public Window(String name, WindowStatus windowStatus) {
        this.windowStatus = windowStatus;
        this.name = name;
    }

    public Window(Room room, String name, WindowStatus windowStatus) {
        this.room=room;
        this.windowStatus = windowStatus;
        this.name = name;
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public WindowStatus getWindowStatus() {
        return windowStatus;
    }

    public void setWindowStatus(WindowStatus windowStatus) {
        this.windowStatus = windowStatus;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}