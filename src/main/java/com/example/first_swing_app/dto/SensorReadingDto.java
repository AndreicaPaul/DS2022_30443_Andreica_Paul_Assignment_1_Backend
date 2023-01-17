package com.example.first_swing_app.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class SensorReadingDto {

    private Long timestamp;

    private Long device_id;

    private float measurement_value;
}
