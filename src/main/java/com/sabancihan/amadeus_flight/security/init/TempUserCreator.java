package com.sabancihan.amadeus_flight.security.init;

import com.sabancihan.amadeus_flight.dto.request.RegisterRequest;
import com.sabancihan.amadeus_flight.model.User;
import com.sabancihan.amadeus_flight.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TempUserCreator implements CommandLineRunner {
    private final AuthService authService;

    @Override
    public void run(String... args) throws Exception {
        authService.registerUser(
               RegisterRequest.builder()
                        .username("admin")
                        .password("trial")
                        .role(User.Role.ADMIN)

                        .build()
        );

        authService.registerUser(
                RegisterRequest.builder()
                        .username("user")
                        .password("trial1")
                        .role(User.Role.USER)
                        .build()
        );


    }
}
