package com.kevincoder.parceltrackingsystem.controller.resp;

import com.kevincoder.parceltrackingsystem.model.Parcel;
import lombok.Data;

import java.util.List;

@Data
public class CheckOutGuestResp {

    public List<Parcel> parcelList;
}
