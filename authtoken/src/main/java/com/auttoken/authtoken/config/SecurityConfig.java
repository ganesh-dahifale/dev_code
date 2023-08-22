package com.auttoken.authtoken.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import com.auttoken.authtoken.filter.AuthenticationFilter;
import com.auttoken.authtoken.filter.JWTAuthorizationFilter;

import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
public class SecurityConfig {
    private final CustomAuthenticationManager customAuthenticationManager;
    @Bean
    SecurityFilterChain defauSecurityFilterChain(HttpSecurity http) throws Exception{
        AuthenticationFilter authenticationFilter = new AuthenticationFilter(customAuthenticationManager);
        authenticationFilter.setFilterProcessesUrl("/home");

        http.csrf((csrf) -> csrf.disable())
        .authorizeHttpRequests((request) -> request.requestMatchers("/home/*").authenticated()
        .requestMatchers("/register").permitAll())
        .httpBasic(Customizer.withDefaults())
        .addFilter(authenticationFilter)
        .addFilterAfter(new JWTAuthorizationFilter(), AuthenticationFilter.class);

        return http.build();
    }
    

    
}
