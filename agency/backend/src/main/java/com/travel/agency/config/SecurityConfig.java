package com.travel.agency.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                //.csrf(csrf -> csrf.disable())
                .headers(headers -> headers.frameOptions(frame -> frame.disable()))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/api/**",
                                "/login",
                                "/error",          // ⭐ 이거 반드시
                                "/h2-console/**"   // 있으면 같이
                        ).permitAll()
                        .requestMatchers("/api/**").permitAll()   // API는 일단 개방
                        .anyRequest().authenticated()
                ).formLogin(form -> form
                        .loginPage("/login")
                        .permitAll()
                );

        return http.build();
    }
}
