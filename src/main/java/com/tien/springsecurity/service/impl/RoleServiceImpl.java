package com.tien.springsecurity.service.impl;


import com.tien.springsecurity.dto.request.PermissionFromRoleRequest;
import com.tien.springsecurity.dto.request.RoleRequest;
import com.tien.springsecurity.dto.response.RoleResponse;
import com.tien.springsecurity.entity.Permission;
import com.tien.springsecurity.entity.Role;
import com.tien.springsecurity.mapper.RoleMapper;
import com.tien.springsecurity.repository.PermissionRepository;
import com.tien.springsecurity.repository.RoleRepository;
import com.tien.springsecurity.service.RoleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleServiceImpl implements RoleService {
    RoleRepository roleRepository;
    RoleMapper roleMapper;
    PermissionRepository permissionRepository;

    @Override
    public RoleResponse createRole(RoleRequest roleRequest) {
        Role role = roleMapper.toRole(roleRequest);
        //lấy ra list permission trong role với set các name của roleReuest
        Set<String> permissionName = roleRequest.getPermissionsName();
        Set<Permission> permissions = new HashSet<>();
        permissionName.forEach(permission -> permissions.add(permissionRepository.findById(permission).get()));
        if(permissions != null) {
            role.setPermissions(permissions);
        }
        return roleMapper.toRoleResponse(roleRepository.save(role));
    }

    @Override
    public List<RoleResponse> listRole() {
        if(roleRepository.findAll().isEmpty()) {
            throw new RuntimeException("data is empty");
        }
        return roleRepository.findAll().stream().map(roleMapper::toRoleResponse).toList();
    }

    @Override
    public void deleteRole(String name) {
        roleRepository.deleteById(name);
    }

    @Override
    public void deletePermissionFromRole(String name) {
        System.out.println("");
    }

    @Override
    public RoleResponse addPermission(PermissionFromRoleRequest permissionFromRoleRequest) {
        return null;
    }
}
