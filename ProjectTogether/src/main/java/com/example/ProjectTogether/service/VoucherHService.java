package com.example.ProjectTogether.service;

import com.example.ProjectTogether.model.HotelModel;
import com.example.ProjectTogether.model.ReservationHotel;
import com.example.ProjectTogether.model.VoucherH;
import com.example.ProjectTogether.repository.ReservationHotelRepository;
import com.example.ProjectTogether.repository.VoucherHRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VoucherHService {
    @Autowired
    private VoucherHRepository voucherHRepository;
    @Autowired
    private ReservationHotelRepository reservationHotelRepository;

    public void addReservationsH(VoucherH voucherH, String username){
        List<ReservationHotel> reservationHotels = reservationHotelRepository.findAll();
        ReservationHotel reservationHotel = reservationHotels.get(reservationHotels.size()-1);
        HotelModel hotelModel = reservationHotel.getRoom().getHotel();
        voucherH.setReservationHotel(reservationHotel);
        voucherH.setUsername(username);
        double totalPrice = calculatePacketPrice(hotelModel.getBasicPrice(),voucherH.getPacket().name(),reservationHotel.getPersonsNumber());
        voucherH.setTotalPrice(totalPrice + (totalPrice*reservationHotel.getRoom().getRoomTypeModel().getProcentPrice()));
        voucherHRepository.save(voucherH);
    }
    private double calculatePacketPrice(double price, String packetType, int numPers){
        double totalPrice = price;
        if (packetType.equals("AI")){
            totalPrice = price*((price*20/100)*numPers);
        }if (packetType.equals("FB")){
            totalPrice = price*((price*15/100)*numPers);
        }if (packetType.equals("HB")){
            totalPrice = price*((price*10/100)*numPers);
        }if (packetType.equals("BB")){
            totalPrice = price*((price*5/100)*numPers);
        }
       return totalPrice;
    }
}
