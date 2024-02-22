package com.kevincoder.parceltrackingsystem.exception;

import lombok.Getter;

@Getter
public class SystemException extends RuntimeException {

    private String errCode;
    private String errMsg;

    public SystemException(String code, String message) {
        this.errCode = code;
        this.errMsg = message;
    }

}
