package com.sns.ss.repository;

import com.sns.ss.entity.Alarm;
import com.sns.ss.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AlarmRepository extends JpaRepository<Alarm, Long> {

    Page<Alarm> findAllByMember(Member member, Pageable pageable);
}
