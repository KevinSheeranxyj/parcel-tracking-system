package com.kevincoder.parceltrackingsystem.controller.req;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AcceptParcelReq {
    private Long guestId;
    private String description;
    private LocalDate arrivalDate;

}
