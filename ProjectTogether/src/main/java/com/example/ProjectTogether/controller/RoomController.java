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

    // public void createRooms(long idRoomType, long idHotel, int numRooms)


    @PostMapping("/room/{idRoomType}/{idHotel}/{numRooms}")
    public void addRoom(@PathVariable(name = "idRoomType") Long idRoomType,@PathVariable(name = "idHotel") Long idHotel,@PathVariable(name = "numRooms") int numRooms){
        reservationService.createRooms(idRoomType, idHotel, numRooms);
    }

    @DeleteMapping("/room/{id}")
    public void deleteRoom(@PathVariable(name = "id") Long id) {
        roomRepository.deleteById(id);
    }

    @GetMapping("/rooms/{id}")
    public List<RoomModel> getRooms(@PathVariable(name = "id") Long id) {
        return reservationService.roomModels(id);
    }

    @GetMapping("/room/{id}")
    public RoomModel getRoomById(@PathVariable(name = "id") Long id) {
        return roomRepository.findById(id).orElse(null);
    }

    @PostMapping("/reserve/{id}")
    public void reserve(@RequestBody ReservationHotel reservation,@PathVariable(name = "id") Long id) {
        reservationService.reserve(reservation,id);
    }
}
