package com.sns.ss.service;

import com.sns.ss.entity.Member;
import com.sns.ss.exception.SnsApplicationException;
import com.sns.ss.repository.MemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DisplayName("비즈니스 로직 테스트 - 회원등록, 로그인")
@ExtendWith(MockitoExtension.class)
class MemberServiceTest {


    @InjectMocks
    private MemberService memberService;
    @Mock
    private MemberRepository memberRepository;
    @Mock
    private PasswordEncoder encoder;


    @DisplayName("회원가입 - 정상")
    @Test
    void member_register_service_test_isOk(){
        //Given
        String email = "email";
        String name = "name";
        String password = "password";
        when(memberRepository.findByEmail(email)).thenReturn(Optional.empty());
        when(encoder.encode(password)).thenReturn("BCryptPassword");
        when(memberRepository.save(any())).thenReturn(Optional.of(addFixture(email,name, password)));

        //When & Then
        Assertions.assertDoesNotThrow(() -> memberService.join(email, name, password));
    }

    @DisplayName("회원가입 - 동일한 name이 존재할때")
    @Test
    void member_register_service_test_error(){
        //Given
        String email = "email";
        String name = "name";
        String password = "password";
        Member fixture = addFixture(email,name, password);
        when(memberRepository.findByEmail(email)).thenReturn(Optional.of(fixture));
        when(encoder.encode(password)).thenReturn("BCryptPassword");
        when(memberRepository.save(any())).thenReturn(Optional.of(fixture));

        //When & Then
        Assertions.assertThrows(SnsApplicationException.class, () -> memberService.join(email,name, password));
    }

    public static Member addFixture(String name, String email, String password){
        Member result = new Member();
        result.setMemberId(1L);
        result.setEmail(email);
        result.setName(name);
        result.setPassword(password);
        return result;
    }


}