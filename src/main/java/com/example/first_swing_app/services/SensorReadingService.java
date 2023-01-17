package com.example.first_swing_app.services;

import com.example.first_swing_app.models.MeteringDevice;
import com.example.first_swing_app.models.SensorReading;
import com.example.first_swing_app.repositories.MeteringDeviceReadingsRepository;
import com.example.first_swing_app.repositories.MeteringDeviceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class SensorReadingService {

    private final MeteringDeviceReadingsRepository meteringDeviceReadingsRepository;
    private final MeteringDeviceRepository meteringDeviceRepository;

    public void saveReading(SensorReading sensorReading) {
        meteringDeviceReadingsRepository.save(sensorReading);
    }

    public boolean isDeviceLimitExceeded(Long deviceId) {
        Optional<MeteringDevice> meteringDevice = meteringDeviceRepository.findById(deviceId);
        if(!meteringDevice.isPresent() || meteringDevice.get().getMaximumHourlyConsumption() == 0) {
            return false;
        }
        float currentValue = getTotalConsumptionForDeviceId(deviceId);
        System.out.println(meteringDevice.get().getMaximumHourlyConsumption() + " " + currentValue);
        return currentValue > meteringDevice.get().getMaximumHourlyConsumption();
    }

    private float getTotalConsumptionForDeviceId(Long deviceId) {
        List<SensorReading> sensorReadings = meteringDeviceReadingsRepository.findAllByMeteringDeviceId(deviceId);

        float sum = 0;
        for (SensorReading reading : sensorReadings) {
            sum += reading.getMeasurementValue();
        }
        return  sum;
    }
}
