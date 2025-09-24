package com.tien.springsecurity.service.impl;

import com.tien.springsecurity.dto.request.AuthenticationRequest;
import com.tien.springsecurity.dto.response.AuthenticationRespose;
import com.tien.springsecurity.service.AuthenticationService;
import com.tien.springsecurity.service.CustomerUserDetailService;
import com.tien.springsecurity.service.JwtService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationServiceImpl implements AuthenticationService {
    AuthenticationManager authenticationManager;
    JwtServiceImpl jwtService;
    CustomerUserDetailService customerUserDetailService;

    @Override
    public AuthenticationRespose login(AuthenticationRequest authenticationRequest) {
        //Authentication Managermanen laf tring tam cua phaan quyen
        // no lau ve oject de di veryfy bang DaoAuthenticationProvider
        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPass()));
        if (auth.isAuthenticated()) {
            AuthenticationRespose authenticationRespose = new AuthenticationRespose();
            authenticationRespose.setCheckLogin(true);
            authenticationRespose.setToken(jwtService.generateToken(customerUserDetailService.loadUserByUsername(authenticationRequest.getEmail())));
            return authenticationRespose;
        }
        throw new RuntimeException("Invalid login");
    }
}