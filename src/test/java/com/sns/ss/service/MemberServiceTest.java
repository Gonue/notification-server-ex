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
    private BCryptPasswordEncoder encoder;


    @DisplayName("회원가입 - 정상")
    @Test
    void member_register_service_test_isOk(){
        //Given
        String name = "name";
        String password = "password";
        when(memberRepository.findByName(name)).thenReturn(Optional.empty());
        when(encoder.encode(password)).thenReturn("BCryptPassword");
        when(memberRepository.save(any())).thenReturn(Optional.of(addFixture(name, password)));

        //When & Then
        Assertions.assertDoesNotThrow(() -> memberService.join(name, password));
    }

    @DisplayName("회원가입 - 동일한 name이 존재할때")
    @Test
    void member_register_service_test_error(){
        //Given
        String name = "name";
        String password = "password";
        Member fixture = addFixture(name, password);
        when(memberRepository.findByName(name)).thenReturn(Optional.of(fixture));
        when(encoder.encode(password)).thenReturn("BCryptPassword");
        when(memberRepository.save(any())).thenReturn(Optional.of(fixture));

        //When & Then
        Assertions.assertThrows(SnsApplicationException.class, () -> memberService.join(name, password));
    }

    @DisplayName("로그인 - 정상")
    @Test
    void member_login_service_test_isOk(){
        //Given
        String name = "name";
        String password = "password";
        Member fixture = addFixture(name, password);

        when(memberRepository.findByName(name)).thenReturn(Optional.of(fixture));
        //When & Then
        Assertions.assertDoesNotThrow(() -> memberService.login(name, password));
    }

    @DisplayName("로그인 - 입력한 name으로 회원등록한 유저가 없는 경우")
    @Test
    void member_login_service_test_error1(){
        //Given
        String name = "name";
        String password = "password";

        when(memberRepository.findByName(name)).thenReturn(Optional.empty());

        //When & Then
        Assertions.assertThrows(SnsApplicationException.class, () -> memberService.login(name, password));
    }

    @DisplayName("로그인 - 입력한 password가 일치하지 않는 경우")
    @Test
    void member_login_service_test_error2(){
        //Given
        String name = "name";
        String password = "password";
        String wrongPassword = "wrongPassword";
        Member fixture = addFixture(name, password);

        when(memberRepository.findByName(name)).thenReturn(Optional.of(fixture));

        //When & Then
        Assertions.assertThrows(SnsApplicationException.class, () -> memberService.login(name, wrongPassword));
    }


    public static Member addFixture(String name, String password){
        Member result = new Member();
        result.setId(1L);
        result.setName(name);
        result.setPassword(password);
        return result;
    }


}