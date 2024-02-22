package com.kevincoder.parceltrackingsystem.repository;

import com.kevincoder.parceltrackingsystem.model.Parcel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface ParcelRepository extends JpaRepository<Parcel, Long> {

    // Finds parcels for a specific guest that are/aren't picked up
    List<Parcel> findByGuestIdAndPickedUp(Long guestId, boolean pickedUp);

    @Modifying
    @Query("UPDATE Parcel p SET p.pickedUp = TRUE WHERE p.guestId = :guestId AND p.pickedUp = FALSE")
    void updateParcelPickUpStatusByGuestId(@Param("guestId") Long guestId);

}
