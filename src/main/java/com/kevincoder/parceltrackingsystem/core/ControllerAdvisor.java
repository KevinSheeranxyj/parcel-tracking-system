package com.kevincoder.parceltrackingsystem.core;

import com.kevincoder.parceltrackingsystem.domain.api.ApiHeader;
import com.kevincoder.parceltrackingsystem.domain.api.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@ControllerAdvice
public class ControllerAdvisor {


    @ResponseBody
    @ExceptionHandler
    public ApiResponse<?> allExceptions(HttpServletRequest request, Exception e) {
        log.error("ControllerAdvisor {}", request.getRequestURI(), e);
        return new ApiResponse<>(new ApiHeader("0004", "API Unknown error"));
    }


}
