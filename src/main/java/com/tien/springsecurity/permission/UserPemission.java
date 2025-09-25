package com.tien.springsecurity.permission;

import com.tien.springsecurity.entity.User;
import com.tien.springsecurity.repository.UserRepository;
import com.tien.springsecurity.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.io.Serializable;
@Component
@RequiredArgsConstructor
//class chi dinh tao bo may de hasPermission co the chay duoc
public class UserPemission implements PermissionEvaluator {
    private final UserRepository userRepository;
    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        return false;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        int id = (int) targetId; //lay id, userid
        String p = (String) permission; //lay quyen ADMIN
        boolean admin = authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals(p));
        User user = userRepository.findById(id).orElseThrow(() ->  new UsernameNotFoundException("user not found"));
        boolean checkDelete = user.getEmail().equals(authentication.getName());
        if(!checkDelete && admin) {
            return true;
        }
        return false;
    }
}
