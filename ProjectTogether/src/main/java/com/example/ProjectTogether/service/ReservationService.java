package com.example.ProjectTogether.service;

import com.example.ProjectTogether.model.HotelModel;
import com.example.ProjectTogether.model.ReservationHotel;
import com.example.ProjectTogether.model.RoomModel;
import com.example.ProjectTogether.model.RoomTypeModel;
import com.example.ProjectTogether.repository.HotelRepository;
import com.example.ProjectTogether.repository.ReservationHotelRepository;
import com.example.ProjectTogether.repository.RoomRepository;
import com.example.ProjectTogether.repository.RoomTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReservationService {
    @Autowired
    private HotelRepository hotelRepository;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private ReservationHotelRepository reservationHotelRepository;
    @Autowired
    private RoomTypeRepository roomTypeRepository;

    private boolean ifIsReserved(ReservationHotel rezOld, ReservationHotel rezNew){
        if (rezOld.getCheckInDate().equals(rezNew.getCheckInDate()) ||
                (rezOld.getCheckInDate().before(rezNew.getCheckInDate()) && rezOld.getCheckOutDate().after(rezNew.getCheckInDate())) ||
                (rezOld.getCheckInDate().after(rezNew.getCheckOutTime())&& rezOld.getCheckInDate().before(rezOld.getCheckOutDate()) )){
            return false;

        }
        return true;
    }

    private boolean verifyPlaces(RoomModel room, ReservationHotel reservation){
        if (room.getRoomTypeModel().getPlaces() > reservation.getPersonsNumber()){
            return true;
        }
        return false;
    }

    public void reserve(ReservationHotel reservation, long id){
        Optional<HotelModel> hotelModelOptional = hotelRepository.findById(id);
        if (hotelModelOptional.isPresent()){
            HotelModel hotel = hotelModelOptional.get();

            for (RoomModel room: hotel.getRooms()){
                if (verifyPlaces(room,reservation)){
                    for (ReservationHotel reservationH: room.getReservations()){
                        if (!ifIsReserved(reservationH,reservation)){
                            room.getReservations().add(reservation);
                            roomRepository.save(room);
                        }
                    }
                }
            }
        }
    }
    public void createRooms(long idRoomType, long idHotel, int numRooms){
        RoomModel room = new RoomModel();
        Optional<HotelModel> hotelModelOptional = hotelRepository.findById(idHotel);
        Optional<RoomTypeModel> roomTypeModelOptional = roomTypeRepository.findById(idRoomType);
        if (hotelModelOptional.isPresent() && roomTypeModelOptional.isPresent()){
            HotelModel hotel = hotelModelOptional.get();
            RoomTypeModel roomType = roomTypeModelOptional.get();
            for (int r = 1; r <= numRooms; r++){
                room.setHotel(hotel);
                room.setRoomTypeModel(roomType);
                roomRepository.save(room);
            }
        }
    }
}
