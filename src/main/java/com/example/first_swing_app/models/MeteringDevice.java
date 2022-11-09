package com.example.first_swing_app.models;


import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "METERING_DEVICES")
public class MeteringDevice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    private float maximumHourlyConsumption;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
