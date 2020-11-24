package com.example.ProjectTogether.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "voucher")
public class VoucherModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    @OneToMany(cascade = CascadeType.ALL)
    private List<ReservationFlight> reservationFlights = new ArrayList<>();
    private int numberOfTicketsFlight;

    private double totalPrice;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<ReservationFlight> getReservationFlights() {
        return reservationFlights;
    }

    public void setReservationFlights(List<ReservationFlight> reservationFlights) {
        this.reservationFlights = reservationFlights;
    }

    public int getNumberOfTicketsFlight() {
        return numberOfTicketsFlight;
    }

    public void setNumberOfTicketsFlight(int numberOfTicketsFlight) {
        this.numberOfTicketsFlight = numberOfTicketsFlight;
    }


    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
