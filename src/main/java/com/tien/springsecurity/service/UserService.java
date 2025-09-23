package com.tien.springsecurity.service;

import com.tien.springsecurity.dto.request.UserRequest;
import com.tien.springsecurity.dto.response.UserResponse;

public interface UserService {
    UserResponse registerUser(UserRequest userRequest);
}
