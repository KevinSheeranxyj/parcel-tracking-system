package com.kevincoder.parceltrackingsystem.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "parcel")
public class Parcel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "picked_up")
    private boolean pickedUp = false; // pickUp status, true stands for it has been picked up and vice versa
    @Column(name = "description")
    private String description;
    @Column(name = "arrival_date", columnDefinition="DATE")
    private LocalDate arrivalDate;

    @Column(name = "guest_id")
    private Long guestId;


}
