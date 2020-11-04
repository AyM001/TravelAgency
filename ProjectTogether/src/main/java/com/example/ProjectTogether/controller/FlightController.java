package com.example.ProjectTogether.controller;


import com.example.ProjectTogether.model.FlightModel;
import com.example.ProjectTogether.model.ParticipantModel;
import com.example.ProjectTogether.model.ReservationFlight;
import com.example.ProjectTogether.repository.FlightRepository;
import com.example.ProjectTogether.service.FlightService;
import com.example.ProjectTogether.service.ReservationFlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class FlightController {
    @Autowired
    private FlightRepository flightRepository;
    @Autowired
    private FlightService flightService;
    @Autowired
    private ReservationFlightService reservationFlightService;

    @PostMapping("/flights")
    public void addFlight(@RequestBody FlightModel flightModel){
        flightService.createFlight(flightModel);
    }

    @GetMapping("/flights")
    public List<FlightModel> getFlights(){
        return flightRepository.findAll();
    }

    @GetMapping("/flights/{id}")
    public FlightModel getById(@PathVariable(name = "id") Long idFlight) {
        return flightRepository.findById(idFlight).orElse(null);
    }
    @PutMapping("/flights/{id}/{numPers}")  //numPers persoanele care vor sa rezerve bilete la zborul respectiv
    public void saveReservation(@RequestBody ReservationFlight reservation,@PathVariable(name = "id") Long idFlight,@PathVariable(name = "numPers") int numPers){
        reservationFlightService.saveReservation(reservation,idFlight,numPers);
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
