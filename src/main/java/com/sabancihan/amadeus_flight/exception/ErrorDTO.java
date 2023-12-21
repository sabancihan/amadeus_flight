package com.sabancihan.amadeus_flight.exception;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Builder
public class ErrorDTO {
    @NotNull
    private String title;

    @NotNull
    private String message;

    private String timestamp;
    private String path;
    private String method;
}