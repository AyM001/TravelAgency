package com.example.ProjectTogether.service;

import com.example.ProjectTogether.model.FlightModel;
import com.example.ProjectTogether.model.SeatModel;
import com.example.ProjectTogether.repository.FlightRepository;
import com.example.ProjectTogether.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FlightService {
    @Autowired
    private FlightRepository flightRepository;
    @Autowired
    private SeatRepository seatRepository;

    public void createSeats(long id, int seatsNumber, int rows){
        Optional<FlightModel> flightModelOptional = flightRepository.findById(id);
        if (flightModelOptional.isPresent()){
            FlightModel flight = flightModelOptional.get();
            for (int r = 1; r==rows ; r++){
                for (int s = 1; s==seatsNumber;s++){
                    String seatName = createSeatName(s)+r;
                    SeatModel seat = new SeatModel();
                    seat.setSeatName(seatName);
                    flight.getSeats().add(seat);
                }
            }
            flightRepository.save(flight);
        }
    }

    private String createSeatName(int rowNumber){
       char x = 'A';
       for (int i =1; i<rowNumber; i++){
           x = (char) (x + 1);
       }
      String name= ""+x;
       return name;
    }


}

