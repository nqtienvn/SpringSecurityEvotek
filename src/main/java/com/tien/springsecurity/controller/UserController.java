package com.tien.springsecurity.controller;

import com.tien.springsecurity.dto.request.UserRequest;
import com.tien.springsecurity.dto.response.ApiResponse;
import com.tien.springsecurity.dto.response.UserResponse;
import com.tien.springsecurity.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {
    UserService userService;
    @PostMapping("/users")
    public ApiResponse<UserResponse> register(@RequestBody UserRequest userRequest) {
        return ApiResponse.<UserResponse>builder()
                .code(200)
                .message("success")
                .result(userService.registerUser(userRequest))
                .build();
    }
    @GetMapping("/hello")
    public String helloWorld() {
        return "hello world";
    }
}
