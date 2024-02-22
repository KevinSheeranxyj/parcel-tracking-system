package com.kevincoder.parceltrackingsystem.service;

import com.kevincoder.parceltrackingsystem.exception.SystemException;
import com.kevincoder.parceltrackingsystem.model.Guest;
import com.kevincoder.parceltrackingsystem.model.Parcel;
import com.kevincoder.parceltrackingsystem.repository.GuestRepository;
import com.kevincoder.parceltrackingsystem.repository.ParcelRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
public class GuestService {

    @Resource
    GuestRepository guestRepository;

    @Resource
    ParcelRepository parcelRepository;

    public Guest checkIn(Guest guest) {
        // Set checkOutDate to null for clarity
        guest.setCheckOutDate(null);
        return guestRepository.save(guest);
    }

    @Transactional
    public void checkOut(Long guestId) {
        Guest guest = guestRepository.findById(guestId)
                .orElseThrow(() -> new SystemException("0001", "Guest not found."));
        guest.setCheckOutDate(LocalDate.now());
        guestRepository.save(guest);
        // Get parcels ready for pickUp
        List<Parcel> parcelsForGuest = parcelRepository.findByGuestIdAndPickedUp(guestId, false);
        // Update the pickUp status of each parcel
        parcelRepository.updateParcelPickUpStatusByGuestId(guestId);
    }

    public boolean isGuestCurrentlyCheckedIn(Long guestId) {
        return guestRepository.findByIdAndCheckOutDateIsNull(guestId).isPresent();
    }

}
