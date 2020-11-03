package com.example.ProjectTogether.controller;


import com.example.ProjectTogether.model.CityModel;
import com.example.ProjectTogether.model.ParticipantModel;
import com.example.ProjectTogether.repository.CityRepository;
import com.example.ProjectTogether.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class CityController {
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private HotelRepository hotelRepository;

    @PostMapping("/cities")
    public void addCity(@RequestBody CityModel cityModel){
        cityRepository.save(cityModel);
    }

    @GetMapping("/cities")
    public List<CityModel> getCities(){
       return  cityRepository.findAll();
    }


    @GetMapping("/cities/{id}")
    public CityModel getById(@PathVariable(name = "id") Long idCity) {
        return cityRepository.findById(idCity).orElse(null);
    }

    @PutMapping("/cities")
    public void updateCity(@RequestBody CityModel cityModel){
        CityModel updatedCity = cityRepository.findById(cityModel.getId()).orElse(null);
        updatedCity.setName(cityModel.getName());
        cityRepository.save(updatedCity);
    }

    @DeleteMapping("/cities/{id}")
    public void deleteCity(@PathVariable (name = "id") Long id){
        cityRepository.deleteById(id);
    }

}
