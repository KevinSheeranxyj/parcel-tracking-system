package com.kevincoder.parceltrackingsystem.service;

import com.kevincoder.parceltrackingsystem.exception.BusinessException;
import com.kevincoder.parceltrackingsystem.model.Parcel;
import com.kevincoder.parceltrackingsystem.repository.ParcelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParcelService {

    @Autowired
    ParcelRepository parcelRepository;

    @Autowired
    GuestService guestService;

    public void acceptParcel(Parcel parcel) {
        // Check if the guest is currently checked in
        if (!guestService.isGuestCurrentlyCheckedIn(parcel.getGuestId())) {
            throw new BusinessException("0002","Cannot accept parcel. Guest is not currently checked in.");
        }
        parcelRepository.save(parcel);
    }

    public List<Parcel> getParcelsForGuest(Long guestId) {
        return parcelRepository.findByGuestIdAndPickedUp(guestId, false);
    }

}
