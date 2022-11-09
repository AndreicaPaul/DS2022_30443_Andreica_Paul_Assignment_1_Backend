package com.example.first_swing_app.services;

import com.example.first_swing_app.models.MeteringDevice;
import com.example.first_swing_app.repositories.MeteringDeviceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MeteringDeviceService {

    private final MeteringDeviceRepository meteringDeviceRepository;

    public List<MeteringDevice> getAll(){ return meteringDeviceRepository.findAll();}

    public List<MeteringDevice> getAllForLocation(Long locationId) {
        return meteringDeviceRepository.findMeteringDevicesByLocationId(locationId);
    }

    public List<MeteringDevice> getAllForUser(Long userId) {
        return meteringDeviceRepository.findMeteringDevicesByUserId(userId);
    }

    public MeteringDevice saveMeteringDevice(MeteringDevice meteringDevice) {
        return meteringDeviceRepository.save(meteringDevice);
    }

    public void deleteMeteringDevice(Long deviceId) {
        meteringDeviceRepository.deleteById(deviceId);
    }
}
