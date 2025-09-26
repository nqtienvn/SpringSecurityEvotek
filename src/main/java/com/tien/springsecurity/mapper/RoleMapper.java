package com.tien.springsecurity.mapper;

import com.tien.springsecurity.dto.request.RoleRequest;
import com.tien.springsecurity.dto.response.RoleResponse;
import com.tien.springsecurity.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest roleRequest);
    RoleResponse toRoleResponse(Role role);
}
