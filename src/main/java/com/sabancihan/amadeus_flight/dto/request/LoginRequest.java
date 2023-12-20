package com.sabancihan.amadeus_flight.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class LoginRequest {

    @NotNull
    private String username;

    @NotNull
    private String password;
}
