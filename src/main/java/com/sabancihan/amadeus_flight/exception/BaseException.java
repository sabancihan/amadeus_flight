package com.sabancihan.amadeus_flight.exception;

import lombok.Getter;

@Getter
public class BaseException extends RuntimeException {
    private final Integer code;
    private final String title;

    public BaseException(Integer code, String title, String message) {
        super(message);
        this.code = code;
        this.title = title;
    }


}