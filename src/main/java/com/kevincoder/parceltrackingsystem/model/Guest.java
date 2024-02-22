package com.kevincoder.parceltrackingsystem.model;


import javax.persistence.*;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "guest")
public class Guest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "check_in_date", columnDefinition="DATE")
    private LocalDate checkInDate;
    @Column(name = "check_out_date", columnDefinition="DATE")
    private LocalDate checkOutDate; // Considered checked out if the checkOutDate in the past

}
