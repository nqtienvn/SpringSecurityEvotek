package com.tien.springsecurity.service.impl;

import com.tien.springsecurity.constant.RoleE;
import com.tien.springsecurity.dto.request.UserRequest;
import com.tien.springsecurity.dto.request.UserRoleRequest;
import com.tien.springsecurity.dto.response.UserResponse;
import com.tien.springsecurity.entity.Role;
import com.tien.springsecurity.entity.User;
import com.tien.springsecurity.mapper.UserMapper;
import com.tien.springsecurity.repository.RoleRepository;
import com.tien.springsecurity.repository.UserRepository;
import com.tien.springsecurity.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    UserRepository userRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Override
    public UserResponse registerUser(UserRequest userRequest) {
        if (userRepository.existsUserByEmail(userRequest.getEmail())) {
            throw new RuntimeException("user is existed");
        }
        User user = userMapper.toUser(userRequest);
        user.setPass(passwordEncoder.encode(user.getPassword()));
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findById(RoleE.USER.toString()).orElseThrow());
        user.setRoles(roles);
        userRepository.save(user);
        return userMapper.toUserResponse(user);
    }

    @Override
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserResponse updateUser(UserRequest userRequest, int id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("user not found"));
        user = userMapper.updateUserFromRequest(userRequest, user);
        user.setPass(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return userMapper.toUserResponse(user);
    }

    @Override
    public List<UserResponse> getUser() {
        List<User> users = userRepository.findAll();
        List<UserResponse> userResponses = new ArrayList<>();
        users.forEach(user -> userResponses.add(userMapper.toUserResponse(user)));
        return userResponses;
    }

    @Override
    public UserResponse getMyInfo() {
        User userDetails = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userMapper.toUserResponse(userDetails);
    }

    @Override
    public UserResponse updateRoleforUser(int id, UserRoleRequest userRoleRequest) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Set<Role> newRoles = new HashSet<>();
        for (String roleName : userRoleRequest.getRoleName()) {
            Role role = roleRepository.findByName(roleName)
                    .orElseThrow(() -> new RuntimeException("Role not found: " + roleName));
            newRoles.add(role);
        }

        user.setRoles(newRoles); // xoá hết role cũ, set danh sách mới

        return userMapper.toUserResponse(userRepository.save(user));
    }

    @Override
    public UserResponse addRoleUser(int id, UserRoleRequest userRoleRequest) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        for (String roleName : userRoleRequest.getRoleName()) {
            Role role = roleRepository.findByName(roleName)
                    .orElseThrow(() -> new RuntimeException("Role not found: " + roleName));
            user.getRoles().add(role); // thêm mà không xoá cái cũ
        }

        return userMapper.toUserResponse(userRepository.save(user));
    }
}
