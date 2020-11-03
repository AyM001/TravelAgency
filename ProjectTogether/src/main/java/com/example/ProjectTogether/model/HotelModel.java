package com.example.ProjectTogether.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "Hotels")
public class HotelModel {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String name;
  private int stars;
  private String description;
  private Date checkInDate;
  private Date checkOutDate;
  private Time checkInTime;
  private Time checkOutTime;
  private String paket;

  @ManyToOne(fetch = FetchType.EAGER)
  @JsonIgnoreProperties("hotelModelList")
  private CityModel cityModel;

  @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL, mappedBy = "hotel")
  @JsonIgnoreProperties("hotel")
  private List<PhotoHotelModel> photos;

  public HotelModel() {
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

  public int getStars() {
    return stars;
  }

  public void setStars(int stars) {
    this.stars = stars;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Date getCheckInDate() {
    return checkInDate;
  }

  public void setCheckInDate(Date checkInDate) {
    this.checkInDate = checkInDate;
  }

  public Date getCheckOutDate() {
    return checkOutDate;
  }

  public void setCheckOutDate(Date checkOutDate) {
    this.checkOutDate = checkOutDate;
  }

  public Time getCheckInTime() {
    return checkInTime;
  }

  public void setCheckInTime(Time checkInTime) {
    this.checkInTime = checkInTime;
  }

  public Time getCheckOutTime() {
    return checkOutTime;
  }

  public void setCheckOutTime(Time checkOutTime) {
    this.checkOutTime = checkOutTime;
  }

  public CityModel getCityModel() {
    return cityModel;
  }

  public String getPaket() {
    return paket;
  }

  public void setPaket(String paket) {
    this.paket = paket;
  }

  public void setCityModel(CityModel cityModel) {
    this.cityModel = cityModel;
  }

  public List<PhotoHotelModel> getPhotos() {
    return photos;
  }

  public void setPhotos(List<PhotoHotelModel> photos) {
    this.photos = photos;
  }
}
