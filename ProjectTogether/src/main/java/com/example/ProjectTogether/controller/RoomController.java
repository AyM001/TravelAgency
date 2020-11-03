package com.example.ProjectTogether.controller;

import com.example.ProjectTogether.model.HotelModel;
import com.example.ProjectTogether.model.ReservationHotel;
import com.example.ProjectTogether.model.RoomModel;
import com.example.ProjectTogether.repository.RoomRepository;
import com.example.ProjectTogether.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class RoomController {
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private ReservationService reservationService;


    @PostMapping("/room")
    public void addRoom(@RequestBody RoomModel model) {
        roomRepository.save(model);
    }

    @DeleteMapping("/room/{id}")
    public void deleteRoom(@PathVariable(name = "id") Long id) {
        roomRepository.deleteById(id);
    }

    @GetMapping("/room")
    public List<RoomModel> getRooms() {
        return roomRepository.findAll();
    }

    @GetMapping("/room/{id}")
    public RoomModel getRoomById(@PathVariable(name = "id") Long id) {
        return roomRepository.findById(id).orElse(null);
    }

    @PutMapping("/room")
    public void UpdateRoom(@RequestBody RoomModel roomModel) {
        RoomModel roomUpdate = roomRepository.findById(roomModel.getId()).orElse(null);
        roomUpdate.setNumber(roomModel.getNumber());
        roomUpdate.setDescription(roomModel.getDescription());
        roomUpdate.setRoomTypeModel(roomModel.getRoomTypeModel());
        roomRepository.save(roomModel);
    }
    @PostMapping("/reserve{id}")
    public void reserve(@RequestBody ReservationHotel reservation,@PathVariable(name = "id") Long id) {
        reservationService.reserve(reservation,id);
    }
}
