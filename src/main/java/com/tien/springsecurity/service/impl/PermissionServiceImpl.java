package com.tien.springsecurity.service.impl;


import com.tien.springsecurity.dto.request.PermissionRequest;
import com.tien.springsecurity.dto.response.PermissionResponse;
import com.tien.springsecurity.mapper.PermissionMapper;
import com.tien.springsecurity.repository.PermissionRepository;
import com.tien.springsecurity.service.PermissionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissionServiceImpl implements PermissionService {
    PermissionRepository permissionRepository;
    PermissionMapper permissionMapper;

    @Override
    public PermissionResponse createPermission(PermissionRequest permissionRequest) {
        return permissionMapper.toPermissionResponse(permissionRepository.save(permissionMapper.toPermission(permissionRequest)));
    }

    @Override
    public List<PermissionResponse> getAllPermission() {
        if(permissionRepository.findAll().isEmpty()) {
            throw new RuntimeException("data is empty");
        }
        return permissionRepository.findAll().stream().map(permissionMapper::toPermissionResponse).toList();
    }

    @Override
    public void deletePermission(String name) {
        permissionRepository.deleteById(name);
    }
}
