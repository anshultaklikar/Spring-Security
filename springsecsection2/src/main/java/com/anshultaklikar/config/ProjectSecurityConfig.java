package com.anshultaklikar.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class ProjectSecurityConfig {
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        // Below are the custom security configurations
        http.authorizeHttpRequests((requests) -> {
            requests.requestMatchers("/myAccount", "/myBalance", "/myCards", "/myLoans").authenticated();
            requests.requestMatchers("/contact", "/notices").permitAll();
        });
        http.formLogin(withDefaults());
        http.httpBasic(withDefaults());
        return http.build();

//        // Configuration to Deny All Requests
//        http
//                .authorizeHttpRequests(requests -> requests
//                        .anyRequest().denyAll()
//                )
//                .formLogin(withDefaults())
//                .httpBasic(withDefaults());
//        return http.build();

//        // Configuration to allow all Requests
//        http
//                .authorizeHttpRequests(requests -> {
//                    requests.anyRequest().permitAll();
//                })
//                .formLogin(withDefaults())
//                .httpBasic(withDefaults());
//        return http.build();
    }
}
