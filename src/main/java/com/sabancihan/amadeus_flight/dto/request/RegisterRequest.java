package com.sabancihan.amadeus_flight.dto.request;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class RegisterRequest {
    @NotNull
    private String username;

    @NotNull
    private String password;
}
