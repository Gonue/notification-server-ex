package com.sns.ss.repository;

import com.sns.ss.entity.Post;
import com.sns.ss.entity.PostComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PostCommentRepository extends JpaRepository <PostComment, Long> {

    Page<PostComment> findAllByPost(Post post, Pageable pageable);

    @Transactional
    @Modifying
    void deleteAllByPost(@Param("post") Post post);

}
