package com.kevincoder.parceltrackingsystem.controller.req;

import lombok.Data;

import java.time.LocalDate;

@Data
public class GuestCheckInReq {

    private String name;
    private LocalDate checkInDate;

}
