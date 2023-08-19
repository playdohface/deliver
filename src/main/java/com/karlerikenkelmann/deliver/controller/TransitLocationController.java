package com.karlerikenkelmann.deliver.controller;

import com.karlerikenkelmann.deliver.entity.TransitLocation;
import com.karlerikenkelmann.deliver.repository.TransitLocationRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("api/transitlocation")
@AllArgsConstructor
@CrossOrigin("http://localhost:4200")
public class TransitLocationController {

    private TransitLocationRepository transitLocationRepository;
    @GetMapping("/dropoff")
    ResponseEntity<List<TransitLocation>> getDropOffStations() {
        return ResponseEntity.ok(this.transitLocationRepository.findDropOffLocations());
    }

    @GetMapping
    ResponseEntity<List<TransitLocation>> findAll() {
        return ResponseEntity.ok(transitLocationRepository.findAll());
    }

}
