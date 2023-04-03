package com.sns.ss.repository;
import com.sns.ss.entity.Member;
import com.sns.ss.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Page<Post> findAllByMember(Member member, Pageable pageable);

}
