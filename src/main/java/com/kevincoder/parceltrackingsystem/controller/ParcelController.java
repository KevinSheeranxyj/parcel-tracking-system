package com.kevincoder.parceltrackingsystem.controller;

import com.kevincoder.parceltrackingsystem.constant.ApiHeaderConstant;
import com.kevincoder.parceltrackingsystem.controller.dto.AcceptParcelDto;
import com.kevincoder.parceltrackingsystem.controller.resp.AcceptParcelResp;
import com.kevincoder.parceltrackingsystem.controller.resp.ListParcelsResp;
import com.kevincoder.parceltrackingsystem.domain.api.ApiResponse;
import com.kevincoder.parceltrackingsystem.exception.SystemException;
import com.kevincoder.parceltrackingsystem.model.Parcel;
import com.kevincoder.parceltrackingsystem.service.ParcelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDate;


@Slf4j
@RequestMapping("/api/v1/parcels")
@RestController
public class ParcelController {

    @Resource
    ParcelService parcelService;

    /**
     *
     * To accept a parcel for a guest
     * @return
     */
    @PostMapping
    public ApiResponse<AcceptParcelResp> acceptParcel(@RequestBody AcceptParcelDto acceptParcelDto) {
        log.info("[POST] /parcels {}", acceptParcelDto);
        AcceptParcelResp resp = new AcceptParcelResp();
        try {
            // Common validation
            commonValidation(acceptParcelDto);
            Parcel parcel = new Parcel();
            parcel.setArrivalDate(acceptParcelDto.getArrivalDate());
            parcel.setDescription(acceptParcelDto.getDescription());
            parcel.setGuestId(acceptParcelDto.getGuestId());
            parcelService.acceptParcel(parcel);
            resp.parcel = parcel;
        } catch (Exception e) {
            log.error("Accept parcel failed", e);
            return new ApiResponse<>(ApiHeaderConstant.FAILED);
        }
        return new ApiResponse<>(ApiHeaderConstant.SUCCESS, resp);
    }

    /**
     *
     * To list parcels for a guest
     * @return
     */
    @GetMapping("/{guestId}")
    public ApiResponse<ListParcelsResp> listParcels(@PathVariable("guestId") Long guestId) {
        log.info("[GET] /parcels/{}", guestId);
        ListParcelsResp resp = new ListParcelsResp();
        resp.parcels = parcelService.getParcelsForGuest(guestId);
        return new ApiResponse<>(ApiHeaderConstant.SUCCESS, resp);
    }

    private void commonValidation(AcceptParcelDto dto) {
        if (dto.getArrivalDate().isAfter(LocalDate.now())) {
            throw new SystemException("0003", "Arrival date incorrect");
        }
    }



}
