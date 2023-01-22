/*
package com.wittgroup.kyn.gateway;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {
    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity serverHttpSecurity) {
        serverHttpSecurity.authorizeExchange(exchanges -> exchanges
                        .pathMatchers("/api/profiles/loadUser/**", "/api/profiles/createUser", "/api/users/signUp").permitAll()
                        .anyExchange().authenticated())
                .csrf(Customizer.withDefaults())
                .oauth2Login(Customizer.withDefaults());
        serverHttpSecurity.csrf().disable();
        return serverHttpSecurity.build();
    }
}
*/