package com.sabancihan.amadeus_flight.service.impl;

import com.sabancihan.amadeus_flight.dto.request.LoginRequest;
import com.sabancihan.amadeus_flight.dto.request.RegisterRequest;
import com.sabancihan.amadeus_flight.dto.response.JwtResponse;
import com.sabancihan.amadeus_flight.model.User;
import com.sabancihan.amadeus_flight.repository.UserRepository;
import com.sabancihan.amadeus_flight.security.jwt.JwtUtils;
import com.sabancihan.amadeus_flight.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j

public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;
    @Override
    public JwtResponse authenticateUser(LoginRequest loginRequest) {

        String username = loginRequest.getUsername();

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        log.info("User {} successfully authenticated",username);

        String jwt = jwtUtils.generateToken(authentication);
        log.info("Token generated for user {}",username);




        return JwtResponse.builder()
                .username(username)
                .token(jwt)
                .build();


    }

    @Override
    public void registerUser(RegisterRequest registerRequest) {
        userRepository.save(
                User.builder()
                        .username(registerRequest.getUsername())
                        .password(passwordEncoder.encode(registerRequest.getPassword()))
                        .role(registerRequest.getRole())
                        .build()
        );
    }
}
