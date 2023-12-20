package com.sabancihan.amadeus_flight.service;

import com.sabancihan.amadeus_flight.dto.request.LoginRequest;
import com.sabancihan.amadeus_flight.dto.request.RegisterRequest;
import com.sabancihan.amadeus_flight.dto.response.JwtResponse;

public interface AuthService {
    JwtResponse authenticateUser(LoginRequest loginRequest);

    void registerUser(RegisterRequest registerRequest);

}
