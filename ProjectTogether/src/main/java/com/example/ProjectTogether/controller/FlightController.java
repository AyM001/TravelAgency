package com.example.ProjectTogether.controller;


import com.example.ProjectTogether.model.FlightModel;
import com.example.ProjectTogether.model.ParticipantModel;
import com.example.ProjectTogether.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class FlightController {
    @Autowired
    private FlightRepository flightRepository;

    @PostMapping("/flights")
    public void addFlight(@RequestBody FlightModel flightModel){
        flightRepository.save(flightModel);
    }

    @GetMapping("/flights")
    public List<FlightModel> getFlights(){
        return flightRepository.findAll();
    }

    @GetMapping("/flights/{id}")
    public FlightModel getById(@PathVariable(name = "id") Long idFlight) {
        return flightRepository.findById(idFlight).orElse(null);
    }

    @PutMapping("/flights")
    public void updateFlight(@RequestBody FlightModel flightModel){
        FlightModel updatedFlight = flightRepository.findById(flightModel.getId()).orElse(null);
        updatedFlight.setName(flightModel.getName());
        updatedFlight.setVacancies(flightModel.getVacancies());
        updatedFlight.setDepartureDay(flightModel.getDepartureDay());
        updatedFlight.setDepartureHour(flightModel.getDepartureHour());
        updatedFlight.setReturnDay(flightModel.getReturnDay());
        updatedFlight.setReturnHour(flightModel.getReturnHour());
        updatedFlight.setAirportModel(flightModel.getAirportModel());
        flightRepository.save(updatedFlight);
    }

    @DeleteMapping("/flights/{id}")
    public void deleteCity(@PathVariable (name = "id") Long id){
        flightRepository.deleteById(id);
    }
}
