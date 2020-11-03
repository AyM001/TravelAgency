package com.example.ProjectTogether.controller;


import com.example.ProjectTogether.model.CountryModel;
import com.example.ProjectTogether.model.HotelModel;
import com.example.ProjectTogether.model.files.ResponseFile;
import com.example.ProjectTogether.repository.HotelRepository;
import com.example.ProjectTogether.service.PhotoHotelStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
public class HotelController {
  @Autowired
  private HotelRepository hotelRepository;
  @Autowired
  private PhotoHotelStorageService photoHotelStorageService;
  @GetMapping("/hotels")
  public List<HotelModel> getHotels() {
    return hotelRepository.findAll();
  }

  @GetMapping("/hotels/{id}")
  public HotelModel getHotel(@PathVariable(name = "id") Long id) {
    return hotelRepository.findById(id).orElse(null);
  }


  @PostMapping("/hotels")
  public void addHotel(@RequestBody HotelModel hotel) {
    hotelRepository.save(hotel);
  }

  @DeleteMapping("/hotels/{id}")
  public void deleteHotel(@PathVariable(name = "id") Long hotelId) {
    hotelRepository.deleteById(hotelId);
  }

  @PutMapping("/hotels")
  public void updateHotel(@RequestBody HotelModel hotel) {
    HotelModel updatedHotel = hotelRepository.findById(hotel.getId()).orElse(null);
    updatedHotel.setName(hotel.getName());
    updatedHotel.setStars(hotel.getStars());
    updatedHotel.setDescription(hotel.getDescription());
    updatedHotel.setPaket(hotel.getPaket());
    hotelRepository.save(hotel);
  }
  @GetMapping("/hotels/photos/{id}")
  public ResponseEntity<List<ResponseFile>> getListFiles(@PathVariable(name = "id") Long id) {
    HotelModel hotelModel = hotelRepository.findById(id).orElse(null);
    List<ResponseFile> files = photoHotelStorageService.getAllHotelphotos(hotelModel).map(dbFile -> {
      String fileDownloadUri = ServletUriComponentsBuilder
              .fromCurrentContextPath()
              .path("/photos/")
              .path(dbFile.getId())
              .toUriString();
      return new ResponseFile(
              dbFile.getName(),
              fileDownloadUri,
              dbFile.getType(),
              dbFile.getData().length);
    }).collect(Collectors.toList());
    return ResponseEntity.status(HttpStatus.OK).body(files);
  }
 }
