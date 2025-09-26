package com.tien.springsecurity.controller;


import com.tien.springsecurity.dto.request.PermissionFromRoleRequest;
import com.tien.springsecurity.dto.request.RoleRequest;
import com.tien.springsecurity.dto.response.ApiResponse;
import com.tien.springsecurity.dto.response.RoleResponse;
import com.tien.springsecurity.service.RoleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleController {
    RoleService roleService;

    @PostMapping("/roles")
    public ApiResponse<RoleResponse> createRole(@RequestBody RoleRequest roleRequest) {
        return ApiResponse.<RoleResponse>builder()
                .code(200)
                .message("tạo role thành công")
                .result(roleService.createRole(roleRequest))
                .build();
    }

    @GetMapping("/roles")
    public ApiResponse<List<RoleResponse>> getAllRole() {
        return ApiResponse.<List<RoleResponse>>builder()
                .code(200)
                .message("tạo role thành công")
                .result(roleService.listRole())
                .build();
    }

    @DeleteMapping("/roles/{name}")
    public ApiResponse<String> delete(@PathVariable(value = "name") String name) {
        roleService.deleteRole(name);
        return ApiResponse.<String>builder()
                .code(1000)
                .message("xóa role thành công")
                .build();
    }

    @DeleteMapping("/role/permission/{name}")
    public ApiResponse<String> deletePermission(@PathVariable(value = "name") String name) {
        return null;
    }

    @PostMapping("/roles/permission")
    public ApiResponse<String> addPermission(@RequestBody PermissionFromRoleRequest permissionRoleRequest) {
        return null;
    }
}
