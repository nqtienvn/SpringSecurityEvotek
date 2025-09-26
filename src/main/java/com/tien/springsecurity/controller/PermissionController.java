package com.tien.springsecurity.controller;


import com.tien.springsecurity.dto.request.PermissionRequest;
import com.tien.springsecurity.dto.response.ApiResponse;
import com.tien.springsecurity.dto.response.PermissionResponse;
import com.tien.springsecurity.service.PermissionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissionController {
    PermissionService permissionService;
    //chi cho thang super admin tao
    @PostMapping("/permissions")
    public ApiResponse<PermissionResponse> createPermission(@RequestBody PermissionRequest permissionRequest) {
        return ApiResponse.<PermissionResponse>builder()
                .code(200)
                .message("tạo permission thành công")
                .result(permissionService.createPermission(permissionRequest))
                .build();
    }

    @GetMapping("/permissions")
    public ApiResponse<List<PermissionResponse>> getAll() {
        return ApiResponse.<List<PermissionResponse>>builder()
                .code(200)
                .message("lấy ra permission thành công")
                .result(permissionService.getAllPermission())
                .build();
    }

    @DeleteMapping("/permissions/{name}")
    public ApiResponse<String> delete(@PathVariable(value = "name") String name) {
        permissionService.deletePermission(name);
        return ApiResponse.<String>builder()
                .code(1000)
                .message("Xóa thành công")
                .build();
    }
}
