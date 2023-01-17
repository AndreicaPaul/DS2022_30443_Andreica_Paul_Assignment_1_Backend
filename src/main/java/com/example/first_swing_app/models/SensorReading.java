package com.example.first_swing_app.models;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "METERING_DEVICE_READINGS")
public class SensorReading {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long timestamp;

    @ManyToOne
    @JoinColumn(name = "device_id")
    private MeteringDevice meteringDevice;

    private float measurementValue;
}
