package com.sabancihan.amadeus_flight.dto.request;


import com.sabancihan.amadeus_flight.model.User;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterRequest {
    @NotNull
    private String username;

    @NotNull
    private String password;

    @NotNull
    private User.Role role;
}
