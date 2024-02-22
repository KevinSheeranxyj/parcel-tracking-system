package com.kevincoder.parceltrackingsystem.repository;

import com.kevincoder.parceltrackingsystem.model.Guest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GuestRepository extends JpaRepository<Guest, Long> {
    // Find guests who haven't checkout
    Optional<Guest> findByIdAndCheckOutDateIsNull(Long id);
}
