package com.tien.springsecurity.mapper;

import com.tien.springsecurity.dto.request.UserRequest;
import com.tien.springsecurity.dto.response.UserResponse;
import com.tien.springsecurity.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper{
    User toUser(UserRequest userRequest);
    UserResponse toUserResponse(User user);
}
