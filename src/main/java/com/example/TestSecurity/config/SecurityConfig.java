package com.example.TestSecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity //SpringSecurity에서 관리되는 클래스로 설정
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        //특정 경로 설정
        http
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/", "/login").permitAll() //모든 사용자 접근 허용
                        .requestMatchers("/admin").hasRole("ADMIN") //ADMIN이라는 role이 있다면 접근 허용
                        .requestMatchers("/my/**").hasAnyRole("ADMIN", "USER") //** : 와일드카드, 여러가지 role(ADMIN,USER) 설정
                        .anyRequest().authenticated() //나머지 경로에 대한 설정
                );

        return http.build(); //http인자를 builder 타입으로 리턴
    }
}
