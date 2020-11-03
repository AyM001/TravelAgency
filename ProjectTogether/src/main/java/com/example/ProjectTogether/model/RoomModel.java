package com.example.ProjectTogether.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "Room")
public class RoomModel {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;
    private int number;
    private String description;


    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnoreProperties("roomModelList")
    private RoomTypeModel roomTypeModel;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public RoomTypeModel getRoomTypeModel() {
        return roomTypeModel;
    }

    public void setRoomTypeModel(RoomTypeModel roomTypeModel) {
        this.roomTypeModel = roomTypeModel;
    }
}
