package com.example.first_swing_app.security.service;

import com.example.first_swing_app.models.Location;
import com.example.first_swing_app.repositories.LocationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class LocationService {

    private final LocationRepository locationRepository;

    public List<Location> getAll() {
        return locationRepository.findAll();
    }
}
