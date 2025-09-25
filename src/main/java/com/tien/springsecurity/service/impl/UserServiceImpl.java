package com.tien.springsecurity.service.impl;

import com.tien.springsecurity.dto.request.UserRequest;
import com.tien.springsecurity.dto.response.UserResponse;
import com.tien.springsecurity.entity.Role;
import com.tien.springsecurity.entity.User;
import com.tien.springsecurity.mapper.UserMapper;
import com.tien.springsecurity.repository.UserRepository;
import com.tien.springsecurity.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    UserRepository userRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;

    @Override
    public UserResponse registerUser(UserRequest userRequest) {
        if (userRepository.existsUserByEmail(userRequest.getEmail())) {
            throw new RuntimeException("user is existed");
        }
        User user = userMapper.toUser(userRequest);
        user.setPass(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.USER);
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
}
