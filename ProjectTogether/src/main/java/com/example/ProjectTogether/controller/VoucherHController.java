package com.example.ProjectTogether.controller;

import com.example.ProjectTogether.model.ReservationHotel;
import com.example.ProjectTogether.model.VoucherH;
import com.example.ProjectTogether.model.VoucherModel;
import com.example.ProjectTogether.repository.ReservationHotelRepository;
import com.example.ProjectTogether.repository.VoucherHRepository;
import com.example.ProjectTogether.repository.VoucherRepository;
import com.example.ProjectTogether.service.ReservationService;
import com.example.ProjectTogether.service.VoucherHService;
import com.example.ProjectTogether.service.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
public class VoucherHController {
@Autowired
private VoucherHService voucherHService;
@Autowired
private VoucherHRepository voucherRepository;
    @Autowired
private ReservationHotelRepository reservationHotelRepository;
    @Autowired
    private ReservationService reservationService;
//@PostMapping("/voucherh/{id}")
//public void save (@RequestBody List<ReservationHotel> reservationHotelList, @PathVariable(name = "id") long id){
//voucherHService.addReservationsH(reservationHotelList, id);
//
//}
    @PostMapping("/voucherh")
    public void save (@RequestBody VoucherH voucherH) {
        List<ReservationHotel> reservationHotels = reservationHotelRepository.findAll();
        voucherH.setReservationHotel(reservationHotels.get(reservationHotels.size()-1));
        voucherRepository.save(voucherH);
    }
    @PostMapping("/reserve/hotel/voucher/{id}/{dateIn}/{dateOut}/{numPers}/{username}")
    public void reserve(@RequestBody VoucherH voucherH, @PathVariable(name = "id") Long id, @PathVariable(name = "dateIn") String dateIn, @PathVariable(name = "dateOut") String dateOut,@PathVariable(name = "numPers") int numPers, @PathVariable(name = "username") String username) throws InterruptedException {
        ReservationHotel reservation = new ReservationHotel();
        reservation.setCheckInDate(java.sql.Date.valueOf(dateIn));
        reservation.setCheckOutDate(java.sql.Date.valueOf(dateOut));
        reservation.setPersonsNumber(numPers);
        reservationService.reserve(reservation,id, voucherH,username);
    }
//    @PostMapping("/reservation/hotel/{username}")
//    public void reserve(@RequestBody ReservationHotel reservationHotel, @PathVariable(name = "username") String username){
//
//    }

    @GetMapping("/voucherh/{id}")
    public VoucherH getVoucher(@PathVariable(name = "id") long id){
        System.out.println("test");
        Optional<VoucherH> voucherH = voucherRepository.findById(id);
        VoucherH voucherh = new VoucherH();
        if (voucherH.isPresent()){
            voucherh = voucherH.get();
        }
        return voucherh;
    }
    @GetMapping("/voucherh")
    public List<VoucherH> getAll(){
    return voucherRepository.findAll();
    }
    @DeleteMapping("/voucherh/{id}")
    public void delete(@PathVariable(name = "id") long id){
    voucherRepository.deleteById(id);
    }

}
