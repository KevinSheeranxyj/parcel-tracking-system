package com.kevincoder.parceltrackingsystem.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ParcelDto {
  public Long id;
  public String description;
  public String arrivalDate;
  public Long guestId;
}
