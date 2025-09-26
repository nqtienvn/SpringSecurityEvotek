package com.tien.springsecurity.mapper;


import com.tien.springsecurity.dto.request.PermissionRequest;
import com.tien.springsecurity.dto.response.PermissionResponse;
import com.tien.springsecurity.entity.Permission;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest request);
    PermissionResponse toPermissionResponse(Permission permission);
}
