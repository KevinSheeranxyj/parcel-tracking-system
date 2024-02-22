package com.kevincoder.parceltrackingsystem.controller;

import com.kevincoder.parceltrackingsystem.constant.ApiHeaderConstant;
import com.kevincoder.parceltrackingsystem.controller.dto.GuestCheckInDto;
import com.kevincoder.parceltrackingsystem.controller.resp.CheckOutGuestResp;
import com.kevincoder.parceltrackingsystem.controller.resp.GuestCheckInResp;
import com.kevincoder.parceltrackingsystem.domain.api.ApiRequest;
import com.kevincoder.parceltrackingsystem.domain.api.ApiResponse;
import com.kevincoder.parceltrackingsystem.exception.SystemException;
import com.kevincoder.parceltrackingsystem.model.Guest;
import com.kevincoder.parceltrackingsystem.service.GuestService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
        ApiRequest<GuestCheckInDto> request = new ApiRequest<>();
        request.query = new GuestCheckInDto();
        Guest guest = new Guest();
        when(guestService.checkIn(any())).thenReturn(guest);
        ApiResponse<GuestCheckInResp> actualResponse = guestController.checkIn(request);
        actualResponse.result.guest = guest;
        assertAll(() -> assertEquals(ApiHeaderConstant.SUCCESS, actualResponse.apiHeader),
                () -> assertEquals(ApiHeaderConstant.SUCCESS.success, actualResponse.apiHeader.success));
    }

    @Test
    void checkOut_success_test() {
        Long guestId = 1L;
        doNothing().when(guestService).checkOut(guestId);
        ApiResponse<CheckOutGuestResp> actualResponse = guestController.checkOut(guestId);
        assertAll(() -> assertEquals(ApiHeaderConstant.SUCCESS, actualResponse.apiHeader),
                () -> assertEquals(ApiHeaderConstant.SUCCESS.success, actualResponse.apiHeader.success));
    }

    @Test
    void checkOut_failed_test() {
        Long guestId = 1L;
        doThrow(new SystemException("0001", "Guest not found")).when(guestService).checkOut(guestId);
        ApiResponse<CheckOutGuestResp> actualResponse = guestController.checkOut(guestId);
        assertEquals(ApiHeaderConstant.FAILED, actualResponse.apiHeader);
    }

}
