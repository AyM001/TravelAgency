package com.example.ProjectTogether.controller;

import com.example.ProjectTogether.model.RoomTypeModel;
import com.example.ProjectTogether.repository.RoomTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class RoomTypeController {
    @Autowired
    private RoomTypeRepository roomTypeRepository;

    @PostMapping("/roomType")
    public void addRoomType(@RequestBody RoomTypeModel model) {
        roomTypeRepository.save(model);
    }

    @DeleteMapping("/roomType/{id}")
    public void deleteRoomType(@PathVariable(name = "id") Long id) {
        roomTypeRepository.deleteById(id);
    }

    @GetMapping("/roomType")
    public List<RoomTypeModel> getRoomType() {
        return roomTypeRepository.findAll();
    }

    @GetMapping("/roomType/{id}")
    public RoomTypeModel getById(@PathVariable(name = "id") Long id) {
        return roomTypeRepository.findById(id).orElse(null);
    }
    @PutMapping("/roomType")
    public void updateRoomType(@RequestBody RoomTypeModel roomTypeModel) {
        RoomTypeModel updateRoomType = roomTypeRepository.findById(roomTypeModel.getId()).orElse(null);
        updateRoomType.setName(roomTypeModel.getName());
        updateRoomType.setPlaces(roomTypeModel.getPlaces());
        updateRoomType.setRoomModelList(roomTypeModel.getRoomModelList());
        roomTypeRepository.save(roomTypeModel);
    }
}