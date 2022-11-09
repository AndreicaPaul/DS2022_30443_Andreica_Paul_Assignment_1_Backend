package com.example.first_swing_app.controllers;

import com.example.first_swing_app.models.Location;
import com.example.first_swing_app.services.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/location")
public class LocationController {

    private final LocationService locationService;

    @GetMapping("/all")
    public List<Location> getAll() {
        return locationService.getAll();
    }
}
