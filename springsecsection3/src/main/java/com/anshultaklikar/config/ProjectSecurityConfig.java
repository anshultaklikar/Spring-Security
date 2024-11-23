package com.anshultaklikar.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
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

    // To use Inmemory security
    /*@Bean
    public InMemoryUserDetailsManager userDetailsService() {
        *//* Approach 1: Where we use withDefaultPasswordEncoder() method
        while creating user details
         *//*
        *//*UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("12345")
                .authorities("admin")
                .build();

        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("12345")
                .authorities("read")
                .build();

        return new InMemoryUserDetailsManager(admin, user);*//*

        *//* Approach 2: Where we use NoOpPasswordEncoder Bean
        while creating user details
         *//*
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        UserDetails admin = User.withUsername("admiin")
                .password("12345")
                .authorities("admin")
                .build();

        UserDetails user = User.withUsername("user")
                .password("12345")
                .authorities("read")
                .build();

        return new InMemoryUserDetailsManager(admin, user);
    }
*/
    // To store user details in DB
    // Comment this bean to use your custom user table and store in DB
    /*@Bean
    public UserDetailsService userDetailsService(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);
    }*/

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
