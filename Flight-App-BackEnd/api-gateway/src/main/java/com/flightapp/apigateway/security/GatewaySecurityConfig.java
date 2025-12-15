package com.flightapp.apigateway.security;

import static com.flightapp.apigateway.security.RoleConstants.ADMIN;
import static com.flightapp.apigateway.security.RoleConstants.USER;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class GatewaySecurityConfig {

    private final JwtAuthFilter jwtFilter;

    public GatewaySecurityConfig(JwtAuthFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {

        return http
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                // you already have separate CorsConfig for CORS
                .authorizeExchange(exchanges -> exchanges

                        // ===== PUBLIC ENDPOINTS =====
                        // (adjust according to where your login/register/health endpoints are)
                        .pathMatchers("/actuator/**").permitAll()
                        .pathMatchers("/eureka/**").permitAll()
                        .pathMatchers("/api/auth/**").permitAll()
                        .pathMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                        // ===== PROTECTED APIs =====
                        // Match the paths you use in application.yml routes:
                        // e.g. /api/flights/**, /api/bookings/**, /api/notify/** etc.

                        .pathMatchers("/api/admin/**").hasRole(ADMIN)
                        .pathMatchers("/api/**").hasAnyRole(ADMIN, USER)

                        // anything else must be authenticated
                        .anyExchange().authenticated()
                )

                // add JWT filter at authentication stage, same as your friend
                .addFilterAt(jwtFilter, SecurityWebFiltersOrder.AUTHENTICATION)

                .httpBasic(ServerHttpSecurity.HttpBasicSpec::disable)
                .formLogin(ServerHttpSecurity.FormLoginSpec::disable)
                .build();
    }
}
