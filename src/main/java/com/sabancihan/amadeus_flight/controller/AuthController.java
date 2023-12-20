package com.sabancihan.amadeus_flight.controller;


import com.sabancihan.amadeus_flight.dto.request.LoginRequest;
import com.sabancihan.amadeus_flight.dto.response.JwtResponse;
import com.sabancihan.amadeus_flight.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public JwtResponse authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        return authService.authenticateUser(loginRequest);


    }

}
