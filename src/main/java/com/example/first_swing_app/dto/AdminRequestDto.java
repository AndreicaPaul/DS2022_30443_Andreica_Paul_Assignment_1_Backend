package com.example.first_swing_app.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class AdminRequestDto {


    @NotEmpty(message = "{token_not_empty}")
    private String token;
}
