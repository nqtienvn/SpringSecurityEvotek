package com.tien.springsecurity.service;


import com.tien.springsecurity.dto.request.PermissionRequest;
import com.tien.springsecurity.dto.response.PermissionResponse;

import java.util.List;

public interface PermissionService {
    PermissionResponse createPermission(PermissionRequest permissionRequest);

    List<PermissionResponse> getAllPermission();

    void deletePermission(String name);
}
