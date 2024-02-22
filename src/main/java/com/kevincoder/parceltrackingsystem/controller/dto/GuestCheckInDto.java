package com.kevincoder.parceltrackingsystem.controller.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class GuestCheckInDto {

    private String name;
    private LocalDate checkInDate;

}
