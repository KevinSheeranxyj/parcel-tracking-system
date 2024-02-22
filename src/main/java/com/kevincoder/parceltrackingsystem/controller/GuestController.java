package com.kevincoder.parceltrackingsystem.controller;

import com.kevincoder.parceltrackingsystem.constant.ApiHeaderConstant;
import com.kevincoder.parceltrackingsystem.controller.req.GuestCheckInReq;
import com.kevincoder.parceltrackingsystem.controller.resp.GuestCheckOutResp;
import com.kevincoder.parceltrackingsystem.controller.resp.GuestCheckInResp;
import com.kevincoder.parceltrackingsystem.domain.api.ApiRequest;
import com.kevincoder.parceltrackingsystem.domain.api.ApiResponse;
import com.kevincoder.parceltrackingsystem.model.Guest;
import com.kevincoder.parceltrackingsystem.service.GuestService;
import com.kevincoder.parceltrackingsystem.service.ParcelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/api/v1/guests")
@RestController
public class GuestController {

    @Autowired
    GuestService guestService;

    @Autowired
    ParcelService parcelService;

    @PostMapping("/check-in")
    public ApiResponse<GuestCheckInResp> checkIn(@RequestBody ApiRequest<GuestCheckInReq> request) {
        log.info("[POST] /check-in {}", request.query);
        GuestCheckInReq checkInDto = request.query;
        GuestCheckInResp resp = new GuestCheckInResp();
        Guest guest = new Guest();
        guest.setCheckInDate(checkInDto.getCheckInDate());
        guest.setName(checkInDto.getName());
        Guest result = guestService.checkIn(guest);
        resp.checkInDate = result.getCheckInDate().toString();
        resp.name = result.getName();
        resp.id = result.getId();
        return new ApiResponse<>(ApiHeaderConstant.SUCCESS, resp);
    }


    @PostMapping("/check-out/{guestId}")
    public ApiResponse<GuestCheckOutResp> checkOut(@PathVariable("guestId") Long guestId) {
        log.info("[POST] /check-out/{}", guestId);
        try {
            guestService.checkOut(guestId);
            return new ApiResponse<>(ApiHeaderConstant.SUCCESS);
        } catch (Exception e) {
            log.error("Check out failed", e);
            return new ApiResponse<>(ApiHeaderConstant.FAILED);
        }
    }

}
