package com.tien.springsecurity.dto.response;

import com.tien.springsecurity.entity.Role;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
    int id;
    String firstName;
    String lastName;
    String email;
    String pass;
    Role role;
}

