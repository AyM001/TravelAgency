package com.example.ProjectTogether.service;

import com.example.ProjectTogether.model.FlightModel;
import com.example.ProjectTogether.model.HotelModel;
import com.example.ProjectTogether.model.ReservationFlight;
import com.example.ProjectTogether.model.VoucherModel;
import com.example.ProjectTogether.repository.FlightRepository;
import com.example.ProjectTogether.repository.HotelRepository;
import com.example.ProjectTogether.repository.VoucherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class VoucherService {
    @Autowired
    private VoucherRepository voucherRepository;
    @Autowired
    private FlightRepository flightRepository;

    public void addReservationsF(ReservationFlight reservationFlight,long id) throws InterruptedException {
          VoucherModel voucher = new VoucherModel();
          voucher.setReservationFlight(reservationFlight);
          voucher.setTotalPrice(calculateTotalPrice(id));
          voucherRepository.save(voucher);

    }
  private double calculateTotalPrice(long id){
        double totalPrice = 0;
        Optional<FlightModel> flightModelOptional = flightRepository.findById(id);
        if (flightModelOptional.isPresent()){
            FlightModel flightModel = flightModelOptional.get();
            totalPrice = totalPrice(flightModel);
        }
        return totalPrice;
  }

  private double totalPrice(FlightModel flightModel){
        double totalPrice = flightModel.getSeatPrice();
        int vacancies = flightModel.getVacancies();
        int seatsNumber= flightModel.getSeatsRowNumber()*flightModel.getRowsNumber();
        double check =(double) seatsNumber/vacancies;
        if (check <= 1.25   ){
            totalPrice = totalPrice + totalPrice*0/100;
        }
        if (check <= 1.66 && check > 1.25){
            totalPrice = totalPrice + totalPrice*15/100;
        }
        if (check <= 2.5 && check > 1.66){
            totalPrice = totalPrice + totalPrice*25/100;
        }
       if (check <= 5 && check > 2.5){
          totalPrice = totalPrice + totalPrice*35/100;
      }
      if ( check > 5){
          totalPrice = totalPrice + totalPrice*45/100;
      }
    return totalPrice;
  }


}
