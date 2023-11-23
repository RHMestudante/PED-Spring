package edu.ifsp.ped.spring.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import edu.ifsp.ped.spring.security.service.MyUserDetailServices;

@Configuration
@EnableWebSecurity

public class WebSecurityConfig{

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.csrf().disable().httpBasic(Customizer.withDefaults()).authorizeRequests(
            authorize -> authorize.requestMatchers(HttpMethod.GET, "/api/v1/ped/aula/").authenticated()
                                  .requestMatchers(HttpMethod.GET, "/api/v1/ped/planosAula").authenticated()
                                  .requestMatchers(HttpMethod.POST, "/api/v1/ped/planosAula").authenticated()
                                  .requestMatchers(HttpMethod.DELETE, "/api/v1/ped/planosAula").authenticated());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();    
    }

    @Bean
    public UserDetailsService myUserDetailsService() {
        return new MyUserDetailServices();
    }

}