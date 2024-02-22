package com.kevincoder.parceltrackingsystem.controller.resp;

import lombok.Data;

@Data
public class AcceptParcelResp {

    public Long id;
    public boolean pickedUp;
    public String description;
    public String arrivalDate;
    public Long guestId;
}
