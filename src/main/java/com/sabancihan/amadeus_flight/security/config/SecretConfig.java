package com.sabancihan.amadeus_flight.security.config;


import io.jsonwebtoken.security.Keys;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.security.Key;

@Configuration
@Getter
@Setter
@ConfigurationProperties(prefix = "amadeus.security")
public class SecretConfig {

        private String secretKey;

        private long tokenExpirationTime;


}
