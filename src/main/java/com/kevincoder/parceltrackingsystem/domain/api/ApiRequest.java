package com.kevincoder.parceltrackingsystem.domain.api;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ApiRequest<T> {

    public T query;

    public ApiRequest(T query) {
        this.query = query;
    }
}
