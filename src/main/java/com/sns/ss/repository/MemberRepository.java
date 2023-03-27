package com.sns.ss.repository;

import com.sns.ss.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository <Member, Long> {

    Optional<Member> findByName(String name);
}
