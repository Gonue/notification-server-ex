package com.sns.ss.repository;

import com.sns.ss.entity.PostLike;
import com.sns.ss.entity.Member;
import com.sns.ss.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository <PostLike, Long> {

    Optional<PostLike> findByMemberAndPost(Member member, Post post);
    @Query(value = "SELECT COUNT(*) from PostLike entity where entity.post = :post")
    Long countByPost(@Param("post") Post post);
}
