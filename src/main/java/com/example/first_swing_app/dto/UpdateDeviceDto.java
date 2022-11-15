package com.example.first_swing_app.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UpdateDeviceDto {
    private String name;

    private String description;

    private float maximumHourlyConsumption;

    private Long user;

    private Long location;
}
