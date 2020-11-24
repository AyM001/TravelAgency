package com.example.ProjectTogether.controller;

import com.example.ProjectTogether.model.ReservationFlight;
import com.example.ProjectTogether.model.VoucherModel;
import com.example.ProjectTogether.repository.VoucherRepository;
import com.example.ProjectTogether.service.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin
public class VoucherController {
    @Autowired
    private VoucherRepository voucherRepository;
    @Autowired
    private VoucherService voucherService;
    @PostMapping("/voucher")
    public void save(@RequestBody VoucherModel voucherModel){
        voucherService.save(voucherModel);
    }
    @PutMapping("/voucher{id}")
    public void addRes(@RequestBody  List<ReservationFlight> reservationFlights,@PathVariable (name = "id") Long id){
        voucherService.addReservationsF(reservationFlights, id);
    }
}
