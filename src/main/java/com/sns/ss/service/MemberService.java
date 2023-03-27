package com.sns.ss.service;

import com.sns.ss.dto.MemberDto;
import com.sns.ss.entity.Member;
import com.sns.ss.exception.ErrorCode;
import com.sns.ss.exception.SnsApplicationException;
import com.sns.ss.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {


    private final MemberRepository memberRepository;

    public MemberDto join(String name, String password){
        memberRepository.findByName(name).ifPresent(it -> {
            throw new SnsApplicationException(ErrorCode.DUPLICATED_MEMBER_NAME, String.format("%s is duplicated", name));
        });

        Member member = memberRepository.save(Member.of(name, password));

        return MemberDto.from(member);
    }

    public String login(String name, String password){
        Member member = memberRepository.findByName(name).orElseThrow(()-> new SnsApplicationException(ErrorCode.DUPLICATED_MEMBER_NAME, ""));

        if(member.getPassword().equals(password)){
            throw new SnsApplicationException(ErrorCode.DUPLICATED_MEMBER_NAME, "");
        }

        return "";
    }
}
