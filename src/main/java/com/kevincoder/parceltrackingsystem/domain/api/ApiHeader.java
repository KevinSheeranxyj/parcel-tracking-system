package com.kevincoder.parceltrackingsystem.domain.api;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ApiHeader {
    public boolean success;
    public String errCode;
    public String errMsg;

    public ApiHeader(boolean success) {
        this.success = success;
    }

    public ApiHeader(String errCode, String errMsg) {
        this.success = false;
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public ApiHeader(boolean success, String errCode, String errMsg) {
        this.success = success;
        this.errCode = errCode;
        this.errMsg = errMsg;
    }
}
