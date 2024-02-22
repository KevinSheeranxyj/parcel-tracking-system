package com.kevincoder.parceltrackingsystem.controller;

import com.kevincoder.parceltrackingsystem.constant.ApiHeaderConstant;
import com.kevincoder.parceltrackingsystem.controller.req.GuestCheckInReq;
import com.kevincoder.parceltrackingsystem.controller.resp.GuestCheckOutResp;
import com.kevincoder.parceltrackingsystem.controller.resp.GuestCheckInResp;
import com.kevincoder.parceltrackingsystem.domain.api.ApiRequest;
import com.kevincoder.parceltrackingsystem.domain.api.ApiResponse;
import com.kevincoder.parceltrackingsystem.exception.BusinessException;
import com.kevincoder.parceltrackingsystem.model.Guest;
import com.kevincoder.parceltrackingsystem.service.GuestService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GuestControllerTest {


    @InjectMocks
    GuestController guestController;

    @Mock
    GuestService guestService;

    @Test
    void checkIn_success_test() {
        ApiRequest<GuestCheckInReq> request = new ApiRequest<>();
        request.query = new GuestCheckInReq();
        Guest guest = new Guest();
        guest.setCheckInDate(LocalDate.now());
        when(guestService.checkIn(any())).thenReturn(guest);
        ApiResponse<GuestCheckInResp> actualResponse = guestController.checkIn(request);
        assertAll(() -> assertEquals(ApiHeaderConstant.SUCCESS, actualResponse.apiHeader),
                () -> assertEquals(ApiHeaderConstant.SUCCESS.success, actualResponse.apiHeader.success));
    }

    @Test
    void checkOut_success_test() {
        Long guestId = 1L;
        doNothing().when(guestService).checkOut(guestId);
        ApiResponse<GuestCheckOutResp> actualResponse = guestController.checkOut(guestId);
        assertAll(() -> assertEquals(ApiHeaderConstant.SUCCESS, actualResponse.apiHeader),
                () -> assertEquals(ApiHeaderConstant.SUCCESS.success, actualResponse.apiHeader.success));
    }

    @Test
    void checkOut_failed_test() {
        Long guestId = 1L;
        doThrow(new BusinessException("0001", "Guest not found")).when(guestService).checkOut(guestId);
        ApiResponse<GuestCheckOutResp> actualResponse = guestController.checkOut(guestId);
        assertEquals(ApiHeaderConstant.FAILED, actualResponse.apiHeader);
    }

}
