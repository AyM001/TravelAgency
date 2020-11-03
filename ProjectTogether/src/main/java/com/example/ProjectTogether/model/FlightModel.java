package com.example.ProjectTogether.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Date;

@Entity
@Table(name = "Flights")
public class FlightModel {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String name;
  private int vacancies;
  private Date departureDay;
  private Date returnDay;
  private Time departureHour;
  private Time returnHour;

  @ManyToOne(fetch = FetchType.EAGER)
  @JsonIgnoreProperties("flightModels")
  private AirportModel airportModel;
  @OneToOne(mappedBy = "flightModel")
  private TripModel tripModel;

  public TripModel getTripModel() {
    return tripModel;
  }

  public FlightModel() {
  }

  public void setTripModel(TripModel tripModel) {
    this.tripModel = tripModel;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getVacancies() {
    return vacancies;
  }

  public void setVacancies(int vacancies) {
    this.vacancies = vacancies;
  }

  public Date getDepartureDay() {
    return departureDay;
  }

  public void setDepartureDay(Date departureDay) {
    this.departureDay = departureDay;
  }

  public Date getReturnDay() {
    return returnDay;
  }

  public void setReturnDay(Date returnDay) {
    this.returnDay = returnDay;
  }

  public Time getDepartureHour() {
    return departureHour;
  }

  public void setDepartureHour(Time departureHour) {
    this.departureHour = departureHour;
  }

  public Time getReturnHour() {
    return returnHour;
  }

  public void setReturnHour(Time returnHour) {
    this.returnHour = returnHour;
  }

  public AirportModel getAirportModel() {
    return airportModel;
  }

  public void setAirportModel(AirportModel airportModel) {
    this.airportModel = airportModel;
  }
}
