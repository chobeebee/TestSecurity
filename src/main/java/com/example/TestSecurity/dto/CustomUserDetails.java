package com.example.TestSecurity.dto;

import com.example.TestSecurity.entity.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class CustomUserDetails implements UserDetails {

    private UserEntity userEntity;

    public CustomUserDetails(UserEntity userEntity) {
        this.userEntity = userEntity;
    }


    //사용자의 특정 권한(Role)을 return
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        
        //Collection 객체 생성
        Collection<GrantedAuthority> collection = new ArrayList<>();
        
        //GrantedAuthority 인터페이스 호출 -> 구현
        collection.add(new GrantedAuthority() {

            @Override
            public String getAuthority() {

                return userEntity.getRole(); //role값 리턴
            }
        });

        return collection;
    }

    @Override
    public String getPassword() {
        return userEntity.getPassword();
    }

    @Override
    public String getUsername() {
        return userEntity.getUsername();
    }
    
    //사용자 id가 만료되었는 지, 잠겨있는 지, 지금 사용 가능한지 체크하는 메서드들
    //강제로 return true;로 수정
    //구현하고자 한다면 DB 테이블에 만료되었는지 체크하는 필드를 넣어서 해당 값을 가져오도록 설정
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
