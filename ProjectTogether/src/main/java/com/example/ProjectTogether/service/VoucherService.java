package com.example.ProjectTogether.service;

import com.example.ProjectTogether.model.ReservationFlight;
import com.example.ProjectTogether.model.ReservationHotel;
import com.example.ProjectTogether.model.VoucherModel;
import com.example.ProjectTogether.repository.VoucherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;
import java.util.Optional;

@Service
public class VoucherService {
    @Autowired
    private VoucherRepository voucherRepository;

    public void save(VoucherModel voucherModel){
        voucherRepository.save(voucherModel);
    }
    public void addReservationsF(List<ReservationFlight> reservationFlights, Long id){
        Optional<VoucherModel> voucherModelOptional = voucherRepository.findById(id);
        if (voucherModelOptional.isPresent()){
            VoucherModel voucherModel = voucherModelOptional.get();
            voucherModel.setReservationFlights(reservationFlights);
            voucherRepository.save(voucherModel);
        }
    }

}
