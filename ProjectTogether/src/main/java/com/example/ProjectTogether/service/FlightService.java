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

    public void createFlight(FlightModel flight){
        flight.setVacancies(flight.getRowsNumber()*flight.getSeatsRowNumber());
        flightRepository.save(flight);
            for (int r = 1; r<=flight.getRowsNumber() ; r++){

                for (int s = 1; s<=flight.getSeatsRowNumber();s++){
                    String seatName = createSeatName(s)+r;
                    SeatModel seat = new SeatModel();
                    seat.setSeatName(seatName);
                    seat.setFlight(flight);
                    seatRepository.save(seat);
                }
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

