package com.tien.springsecurity.controller;

import com.tien.springsecurity.dto.request.UserRequest;
import com.tien.springsecurity.dto.request.UserRoleRequest;
import com.tien.springsecurity.dto.response.ApiResponse;
import com.tien.springsecurity.dto.response.UserResponse;
import com.tien.springsecurity.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    //chỉ cho phép admin truy cập vào enpoint này
    @GetMapping("/users")
    @PreAuthorize("hasAuthority('USER_VIEW')") //cái này sẽ so sánh với thằng userdetails
    public ApiResponse<List<UserResponse>> getAllUser() {
        return ApiResponse.<List<UserResponse>>builder()
                .code(200)
                .message("success")
                .result(userService.getUser())
                .build();
    }
    @PutMapping("/users/{id}") //spEL
    @PreAuthorize("hasAuthority('USER_UPDATE')")
    public ApiResponse<UserResponse> updateUser(@RequestBody UserRequest userRequest,@PathVariable(name = "id") int id) {
        return ApiResponse.<UserResponse>builder()
                .code(200)
                .message("success")
                .result(userService.updateUser(userRequest, id))
                .build();
    }
    @DeleteMapping("/users/{id}")
    @PreAuthorize("hasPermission(#id, 'User', 'USER_DELETE')")
    public ApiResponse<?> deleteUser(@PathVariable(name = "id") int id) {
        userService.deleteUser(id);
        return ApiResponse.builder()
                .code(1000)
                .message("success")
                .build();
    }
    @GetMapping("/users/my-infor")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ApiResponse<UserResponse> myInfo() {
        return ApiResponse.<UserResponse>builder()
                .code(200)
                .message("success")
                .result(userService.getMyInfo())
                .build();
    }
    @PutMapping("/users/role/{id}")
    @PreAuthorize("hasAuthority('USER_ROLE_UPDATE')")
    public ApiResponse<UserResponse> updateRole(@RequestBody UserRoleRequest userRoleRequest, @PathVariable(name = "id") int id) {
        return ApiResponse.<UserResponse>builder()
                .code(200)
                .message("success")
                .result(userService.updateRoleforUser(id, userRoleRequest))
                .build();
    }
    @PostMapping("/users/role/{id}")
    @PreAuthorize("hasAuthority('USER_ROLE_ADD')")
    public ApiResponse<UserResponse> addRole(@RequestBody UserRoleRequest userRoleRequest, @PathVariable(name = "id") int id) {
        return ApiResponse.<UserResponse>builder()
                .code(200)
                .message("success")
                .result(userService.addRoleUser(id, userRoleRequest))
                .build();
    }
}
