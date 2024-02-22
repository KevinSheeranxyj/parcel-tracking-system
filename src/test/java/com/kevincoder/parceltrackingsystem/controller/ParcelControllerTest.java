package com.kevincoder.parceltrackingsystem.controller;

import com.kevincoder.parceltrackingsystem.constant.ApiHeaderConstant;
import com.kevincoder.parceltrackingsystem.controller.req.AcceptParcelReq;
import com.kevincoder.parceltrackingsystem.controller.resp.AcceptParcelResp;
import com.kevincoder.parceltrackingsystem.domain.api.ApiRequest;
import com.kevincoder.parceltrackingsystem.domain.api.ApiResponse;
import com.kevincoder.parceltrackingsystem.exception.BusinessException;
import com.kevincoder.parceltrackingsystem.model.Parcel;
import com.kevincoder.parceltrackingsystem.service.ParcelService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ParcelControllerTest {

    @InjectMocks
    ParcelController parcelController;

    @Mock
    ParcelService parcelService;

    @Test
    void acceptParcel_success_test() {
      ApiRequest<AcceptParcelReq> request = new ApiRequest<>();
      AcceptParcelReq dto = new AcceptParcelReq();
      dto.setDescription("Electronics");
      dto.setArrivalDate(LocalDate.parse("2024-02-12"));
      dto.setGuestId(1L);
      doNothing().when(parcelService).acceptParcel(any());
    request.query = dto;
      ApiResponse<AcceptParcelResp> actualResponse = parcelController.acceptParcel(request);
      assertAll(() -> assertEquals(ApiHeaderConstant.SUCCESS, actualResponse.apiHeader),
              () -> assertEquals(ApiHeaderConstant.SUCCESS.success, actualResponse.apiHeader.success));
    }

    @Test
    void acceptParcel_failed_test() {
      ApiRequest<AcceptParcelReq> request = new ApiRequest<>();
      AcceptParcelReq dto = new AcceptParcelReq();
      dto.setDescription("Electronics");
      dto.setArrivalDate(LocalDate.parse("2024-02-12"));
      dto.setGuestId(1L);
      request.query = dto;
      doThrow(new BusinessException("0002", "Cannot accept parcel. Guest is not currently checked in.")).when(parcelService).acceptParcel(any());
      ApiResponse<AcceptParcelResp> actualResponse = parcelController.acceptParcel(request);
      assertAll(() -> assertEquals(ApiHeaderConstant.FAILED, actualResponse.apiHeader));
    }


    @Test
    void listParcels_success_test() {
      Long guestId = 1L;
      List<Parcel> parcelList = new ArrayList<>();
      when(parcelService.getParcelsForGuest(any())).thenReturn(parcelList);
      ApiResponse<?> actualResponse = parcelController.listParcels(guestId);
      assertAll(() -> assertEquals(ApiHeaderConstant.SUCCESS, actualResponse.apiHeader));
    }


}
