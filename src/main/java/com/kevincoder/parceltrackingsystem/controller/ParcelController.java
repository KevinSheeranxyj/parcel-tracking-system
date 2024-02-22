package com.kevincoder.parceltrackingsystem.controller;

import com.kevincoder.parceltrackingsystem.constant.ApiHeaderConstant;
import com.kevincoder.parceltrackingsystem.controller.req.AcceptParcelReq;
import com.kevincoder.parceltrackingsystem.controller.resp.AcceptParcelResp;
import com.kevincoder.parceltrackingsystem.controller.dto.ParcelDto;
import com.kevincoder.parceltrackingsystem.domain.api.ApiRequest;
import com.kevincoder.parceltrackingsystem.domain.api.ApiResponse;
import com.kevincoder.parceltrackingsystem.exception.BusinessException;
import com.kevincoder.parceltrackingsystem.model.Parcel;
import com.kevincoder.parceltrackingsystem.service.ParcelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


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
    public ApiResponse<AcceptParcelResp> acceptParcel(@RequestBody ApiRequest<AcceptParcelReq> request) {
        log.info("[POST] /parcels {}", request.query);
        AcceptParcelReq acceptParcelReq = request.query;
        AcceptParcelResp resp = new AcceptParcelResp();
        try {
            // Common validation
            commonValidation(acceptParcelReq);
            Parcel parcel = new Parcel();
            parcel.setArrivalDate(acceptParcelReq.getArrivalDate());
            parcel.setDescription(acceptParcelReq.getDescription());
            parcel.setGuestId(acceptParcelReq.getGuestId());
            parcelService.acceptParcel(parcel);
            resp.id = parcel.getId();
            resp.arrivalDate = parcel.getArrivalDate().toString();
            resp.description = parcel.getDescription();
            resp.guestId = parcel.getGuestId();
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
    public ApiResponse<?> listParcels(@PathVariable("guestId") Long guestId) {
      log.info("[GET] /parcels/{}", guestId);
      List<Parcel> parcelList = parcelService.getParcelsForGuest(guestId);
      List<ParcelDto> parcels = parcelList.stream().map(parcel -> new ParcelDto(parcel.getId(), parcel.getDescription(),
        parcel.getArrivalDate().toString(), parcel.getGuestId()))
      .collect(Collectors.toList());
      return new ApiResponse<>(ApiHeaderConstant.SUCCESS, parcels);
    }

    private void commonValidation(AcceptParcelReq dto) {
        if (dto.getArrivalDate().isAfter(LocalDate.now())) {
            throw new BusinessException("0003", "Arrival date incorrect");
        }
    }

}
