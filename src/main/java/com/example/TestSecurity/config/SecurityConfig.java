package com.example.TestSecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity //SpringSecurity에서 관리되는 클래스로 설정
public class SecurityConfig {
    
    //BCrypt 엔코드를 리턴하는 메소드
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        //특정 경로 설정
        http
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/", "/login", "/join", "/joinProc").permitAll() //모든 사용자 접근 허용
                        .requestMatchers("/admin").hasRole("ADMIN") //ADMIN이라는 role이 있다면 접근 허용
                        .requestMatchers("/my/**").hasAnyRole("ADMIN", "USER") //** : 와일드카드, 여러가지 role(ADMIN,USER) 설정
                        .anyRequest().authenticated() //나머지 경로에 대한 설정
                );

        http
                .csrf((auth) -> auth.disable()); //csrf(사이트 위변조 방지)설정 미사용으로 설정
        http
                .formLogin((auth) -> auth.loginPage("/login")
                        .loginProcessingUrl("/loginProc")
                        .permitAll()
                );

        return http.build(); //http인자를 builder 타입으로 리턴
    }
}
