package com.sabancihan.amadeus_flight.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class LoginRequest {

    @NotNull
    private String username;

    @NotNull
    private String password;
}
