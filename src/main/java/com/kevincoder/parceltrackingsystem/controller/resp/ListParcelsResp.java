package com.kevincoder.parceltrackingsystem.controller.resp;

import com.kevincoder.parceltrackingsystem.controller.dto.ParcelDto;
import lombok.Data;

import java.util.List;

@Data
public class ListParcelsResp {

    public List<ParcelDto> parcels;

}
