package com.kevincoder.parceltrackingsystem.domain.api;

import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class ApiResponse<T> {

    public T result;

    public List<T> resultList;

    public ApiHeader apiHeader;

    public ApiResponse(ApiHeader header,T result) {
        this.apiHeader = header;
        this.result = result;
    }

    public ApiResponse(ApiHeader apiHeader) {
        this.apiHeader = apiHeader;
    }

    public ApiResponse(ApiHeader header, List<T> resultList) {
        this.apiHeader = header;
        this.resultList = resultList;
    }
}
