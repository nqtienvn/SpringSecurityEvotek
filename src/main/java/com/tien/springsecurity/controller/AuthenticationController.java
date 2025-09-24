package com.tien.springsecurity.controller;

import com.tien.springsecurity.dto.request.AuthenticationRequest;
import com.tien.springsecurity.dto.response.ApiResponse;
import com.tien.springsecurity.dto.response.AuthenticationRespose;
import com.tien.springsecurity.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    @PostMapping("/login")
    public ApiResponse<AuthenticationRespose> login(@RequestBody AuthenticationRequest authenticationRequest) {
        return ApiResponse.<AuthenticationRespose>builder()
                .result(authenticationService.login(authenticationRequest))
                .message("login thanh cong")
                .code(200)
                .build();
    }
}
