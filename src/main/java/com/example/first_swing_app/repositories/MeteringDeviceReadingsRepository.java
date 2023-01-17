package com.example.first_swing_app.repositories;

import com.example.first_swing_app.models.SensorReading;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MeteringDeviceReadingsRepository extends JpaRepository<SensorReading, Long> {
    List<SensorReading> findAllByMeteringDeviceId(Long deviceId);
}
