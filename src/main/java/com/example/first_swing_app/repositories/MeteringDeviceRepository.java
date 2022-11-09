package com.example.first_swing_app.repositories;

import com.example.first_swing_app.models.MeteringDevice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MeteringDeviceRepository extends JpaRepository<MeteringDevice, Long> {

    List<MeteringDevice> findMeteringDevicesByLocationId(Long locationId);
    List<MeteringDevice> findMeteringDevicesByUserId(Long userId);
}
