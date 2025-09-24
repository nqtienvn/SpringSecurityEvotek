package com.tien.springsecurity.service.impl;

import com.tien.springsecurity.config.JwtProperties;
import com.tien.springsecurity.service.JwtService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;


import java.security.Key;
import java.util.Date;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class JwtServiceImpl implements JwtService {
    JwtProperties jwtProperties;

    public Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtProperties.getSecret());
        return Keys.hmacShaKeyFor(keyBytes);
    }
    @Override
    public String generateToken(String name) {
        return Jwts.builder()
                .subject(name)
                .expiration(new Date(jwtProperties.getExpiration()))
                .signWith(getSignKey(), SignatureAlgorithm.HS512)
                .compact();
    }

    @Override
    public boolean validateToken(String token) {
        try{
            Jwts.parser() //chuẩn bị một cái máy để đọc(máy quét)
                    .setSigningKey(getSignKey()) //cung cấp sercret key để parser
                    .build() //tạo JWT parser haonf chỉnh để JWT phân tích
                    //làm tất cả
                    //táck token thành header.payload.signature
                    //tạo signed mới
                    //so sánh với signed mới
                    .parse(token); // k hợp lệ sẽ ném exception ngay
            return true;
        }catch(Exception ex) {
            throw new RuntimeException("Validate Error");
        }
    }
}
