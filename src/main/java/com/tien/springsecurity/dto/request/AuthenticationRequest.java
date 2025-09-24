package com.tien.springsecurity.dto.request;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AuthenticationRequest {
    String name;
    String password;
}
