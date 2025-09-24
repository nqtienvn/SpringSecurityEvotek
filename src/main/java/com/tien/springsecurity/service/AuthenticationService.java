package com.tien.springsecurity.service;

import com.tien.springsecurity.dto.request.AuthenticationRequest;
import com.tien.springsecurity.dto.response.AuthenticationRespose;

public interface AuthenticationService {
    AuthenticationRespose login(AuthenticationRequest authenticationRequest);
}
