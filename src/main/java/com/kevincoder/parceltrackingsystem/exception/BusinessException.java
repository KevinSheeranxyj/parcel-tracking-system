package com.kevincoder.parceltrackingsystem.exception;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {

    private String errCode;
    private String errMsg;

    public BusinessException(String code, String message) {
        this.errCode = code;
        this.errMsg = message;
    }

}
