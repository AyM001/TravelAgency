package com.example.ProjectTogether.controller;

import com.example.ProjectTogether.model.AirportModel;
import com.example.ProjectTogether.model.ParticipantModel;
import com.example.ProjectTogether.repository.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class AirportController {

    @Autowired
    private AirportRepository airportRepository;

    @PostMapping("/airports")
    public void addAirport(@RequestBody AirportModel airportModel) {
        airportRepository.save(airportModel);
    }

    @GetMapping("/airports")
    public List<AirportModel> getAirports() {
        return airportRepository.findAll();
    }

    @GetMapping("/airports/{id}")
    public AirportModel getById(@PathVariable(name = "id") Long idAirport) {
        return airportRepository.findById(idAirport).orElse(null);
    }

    @PutMapping("/airports")
    public void updateAirport(@RequestBody AirportModel airportModel) {
        AirportModel updatedAirport = airportRepository.findById(airportModel.getId()).orElse(null);
        updatedAirport.setName(airportModel.getName());
        updatedAirport.setCityModel(airportModel.getCityModel());
        updatedAirport.setFlightModels(airportModel.getFlightModels());
        airportRepository.save(updatedAirport);
    }

    @DeleteMapping("/airports/{id}")
    public void deleteAirport(@PathVariable(name = "id") Long id) {
        airportRepository.deleteById(id);
    }
}
