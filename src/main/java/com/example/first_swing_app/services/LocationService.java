package com.example.first_swing_app.services;

import com.example.first_swing_app.models.Location;
import com.example.first_swing_app.models.MeteringDevice;
import com.example.first_swing_app.repositories.LocationRepository;
import com.example.first_swing_app.repositories.MeteringDeviceRepository;
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

    public Location getLocationForId(Long locationId){return locationRepository.getById(locationId);}
}
