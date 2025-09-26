package com.tien.springsecurity.service;


import com.tien.springsecurity.dto.request.PermissionFromRoleRequest;
import com.tien.springsecurity.dto.request.RoleRequest;
import com.tien.springsecurity.dto.response.RoleResponse;

import java.util.List;

public interface RoleService {
    RoleResponse createRole(RoleRequest roleRequest);
    List<RoleResponse> listRole();
    void deleteRole(String name);
    void deletePermissionFromRole(String name);
    RoleResponse addPermission(PermissionFromRoleRequest permissionFromRoleRequest);
}
