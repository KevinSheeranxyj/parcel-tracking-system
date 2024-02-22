package com.kevincoder.parceltrackingsystem.repository;

import com.kevincoder.parceltrackingsystem.model.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface GuestRepository extends JpaRepository<Guest, Long> {

    @Query
    Optional<Guest> findByIdAndCheckOutDateIsNull(Long id); // Find guests who haven't checkout
}
