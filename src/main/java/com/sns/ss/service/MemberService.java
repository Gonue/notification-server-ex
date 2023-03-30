package com.sns.ss.service;

import com.sns.ss.auth.utils.CustomAuthorityUtils;
import com.sns.ss.dto.MemberDto;
import com.sns.ss.entity.Member;
import com.sns.ss.exception.ErrorCode;
import com.sns.ss.exception.SnsApplicationException;
import com.sns.ss.repository.MemberRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final CustomAuthorityUtils customAuthorityUtils;

    public MemberService(MemberRepository memberRepository, PasswordEncoder passwordEncoder, CustomAuthorityUtils customAuthorityUtils) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
        this.customAuthorityUtils = customAuthorityUtils;
    }


    public MemberDto join(String email, String password, String name){
        memberRepository.findByEmail(email).ifPresent(it -> {
            throw new SnsApplicationException(ErrorCode.DUPLICATED_EMAIL, String.format("%s is duplicated", email));
        });

        Member savedMember = memberRepository.save(Member.of(email, name, passwordEncoder.encode(password)));
        List<String> roles = customAuthorityUtils.createRoles(email);
        savedMember.setRoles(roles);
        return MemberDto.from(savedMember);

    }
}

