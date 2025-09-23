package com.tien.springsecurity.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final UserDetailsService userDetailsService;
    @Bean
    //thiết lập mắc định
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        //tắt csrf
        http.csrf(AbstractHttpConfigurer::disable);
        //tắt hết k có enpoint nào quyền truy cập
        http.authorizeHttpRequests(authorizeRequests -> authorizeRequests.requestMatchers(HttpMethod.POST, "/users").permitAll()
                .anyRequest().authenticated());
        //cho hết vè form login
        http.formLogin(Customizer.withDefaults());
        //trả về k phải là html
        http.httpBasic(Customizer.withDefaults());
        //dùng stales là k bao giờ dùng SessionID mà mỗi request phải tự xác thực
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        //cấu hình tạo ra để xác thực db bằng UserDetailsService
        DaoAuthenticationProvider daoProvider = new DaoAuthenticationProvider();
        //dùng password encoder ở login và encode để so sáng với password ở db
        daoProvider.setPasswordEncoder(passwordEncoder());
        //dùng userDetail để tìm user trong db
        daoProvider.setUserDetailsService(userDetailsService);
        return daoProvider;
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
