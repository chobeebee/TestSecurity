package com.example.TestSecurity.service;

import com.example.TestSecurity.dto.CustomUserDetails;
import com.example.TestSecurity.entity.UserEntity;
import com.example.TestSecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository; //필드 주입

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        //DB에서 username 조회
        UserEntity userData = userRepository.findByUsername(username);
        
        //username이 있다면 로그인 진행
        if (userData != null) {
            return new CustomUserDetails(userData);
        }
        
        //저장된 id가 없으면 null 반환
        return null;
    }
}
