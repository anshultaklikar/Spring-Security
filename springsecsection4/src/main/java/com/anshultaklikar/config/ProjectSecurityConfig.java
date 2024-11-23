package com.anshultaklikar.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class ProjectSecurityConfig {
    // To define for which routes we want to add security
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        // Below are the custom security configurations
        http
                .authorizeHttpRequests((requests) -> {
            requests.requestMatchers("/myAccount", "/myBalance", "/myCards", "/myLoans").authenticated();
            requests.requestMatchers("/contact", "/notices", "/register").permitAll();
        })
                .formLogin(withDefaults())
                .httpBasic(withDefaults())
                .csrf(csrf -> {
                    csrf.ignoringRequestMatchers("/contact", "/notices", "/register");
                });
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
