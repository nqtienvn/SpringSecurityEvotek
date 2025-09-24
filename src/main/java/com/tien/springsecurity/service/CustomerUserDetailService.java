package com.tien.springsecurity.service;

import com.tien.springsecurity.entity.User;
import com.tien.springsecurity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerUserDetailService implements UserDetailsService{
    private final UserRepository userRepository;
    @Override
    //hàm thể hiện là dùng thằng UserDetailsService để lấy từ db và trả về thăng UserDetail đó
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmailIs(name).orElseThrow(() ->  new UsernameNotFoundException(name));
        return user;
    }
}
