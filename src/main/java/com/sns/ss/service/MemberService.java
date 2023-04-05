package com.sns.ss.service;

import com.sns.ss.auth.utils.CustomAuthorityUtils;
import com.sns.ss.dto.AlarmDto;
import com.sns.ss.dto.MemberDto;
import com.sns.ss.entity.Member;
import com.sns.ss.exception.ErrorCode;
import com.sns.ss.exception.SnsApplicationException;
import com.sns.ss.repository.AlarmRepository;
import com.sns.ss.repository.MemberRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Transactional
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final CustomAuthorityUtils customAuthorityUtils;
    private final AlarmRepository alarmRepository;

    public MemberService(MemberRepository memberRepository, PasswordEncoder passwordEncoder, CustomAuthorityUtils customAuthorityUtils, AlarmRepository alarmRepository) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
        this.customAuthorityUtils = customAuthorityUtils;
        this.alarmRepository = alarmRepository;
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

    public Page<AlarmDto> alarmList(String email, Pageable pageable){
        Member member =  memberRepository.findByEmail(email).orElseThrow(() -> new SnsApplicationException(ErrorCode.USER_NOT_FOUND, String.format("%s not founded",email)));
        return alarmRepository.findAllByMember(member, pageable).map(AlarmDto::from);
    }
}

