package com.tien.springsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    //thiết lập mắc định
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        //tắt csrf
        http.csrf(AbstractHttpConfigurer::disable);
        //tắt hết k có enpoint nào quyền truy cập
        http.authorizeHttpRequests(authorizeRequests -> authorizeRequests.anyRequest().authenticated());
        //cho hết vè form login
        http.formLogin(Customizer.withDefaults());
        //trả về k phải là html
        http.httpBasic(Customizer.withDefaults());
        //dùng stales là k bao giờ dùng SessionID mà mỗi request phải tự xác thực
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }
    @Bean
    public UserDetailsService userDetailService() {

        //LoadUserFormUserName: Load user từ db lên theo userName

        return new InMemoryUserDetailsManager(); //class kế thừa UserDetailManagẻ kế thừa userDetailService
    }
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
        provider.setUserDetailsService(userDetailService());
        return provider;
    }
}
