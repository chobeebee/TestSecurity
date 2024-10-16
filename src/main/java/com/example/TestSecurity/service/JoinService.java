package com.example.TestSecurity.service;

import com.example.TestSecurity.dto.JoinDTO;
import com.example.TestSecurity.entity.UserEntity;
import com.example.TestSecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JoinService {

    @Autowired
    private UserRepository userRepository; //필드 주입

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public void joinProcess(JoinDTO joinDTO) {
        
        //db에 이미 동일한 username을 가진 회원이 존재하는지 검증 필요
        //UserRepository에서 특정한 커스텀 메서들를 작성해서 회원 검증 진행

        //유저dto를 Entity 변경
        UserEntity data = new UserEntity(); //객체
        
        data.setUsername(joinDTO.getUsername());
        //data.setPassword(joinDTO.getPassword()); //암호화 필요
        data.setPassword(bCryptPasswordEncoder.encode(joinDTO.getPassword())); //암호화
        data.setRole("ROLE_USER"); //회원 자신이 스스로 role 설정 못하기 떄문에 service 단에서 강제로 값을 넣어야 함

        userRepository.save(data); //데이터 입력
    }
}
