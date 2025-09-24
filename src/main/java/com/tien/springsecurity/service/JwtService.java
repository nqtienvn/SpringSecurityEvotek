package com.tien.springsecurity.service;

public interface JwtService {
   String generateToken(String name);
    boolean validateToken(String token);
}
