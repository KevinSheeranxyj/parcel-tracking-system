package com.kevincoder.parceltrackingsystem.controller.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AcceptParcelDto {
    private Long guestId;
    private String description;
    private LocalDate arrivalDate;

}
