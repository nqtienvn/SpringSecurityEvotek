package com.tien.springsecurity.service;

import com.tien.springsecurity.dto.request.UserRequest;
import com.tien.springsecurity.dto.request.UserRoleRequest;
import com.tien.springsecurity.dto.response.UserResponse;

import java.util.List;

public interface UserService {
    UserResponse registerUser(UserRequest userRequest);
    void deleteUser(int id);
    UserResponse updateUser(UserRequest userRequest, int id);
    List<UserResponse> getUser();
    UserResponse getMyInfo();
    UserResponse updateRoleforUser(int id, UserRoleRequest userRoleRequest);
    UserResponse addRoleUser(int id, UserRoleRequest userRoleRequest);
}
