package com.example.ProjectTogether.controller;

import com.example.ProjectTogether.model.RoomModel;
import com.example.ProjectTogether.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class RoomController {
    @Autowired
    private RoomRepository roomRepository;


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
}
