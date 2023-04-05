package com.sns.ss.repository;

import com.sns.ss.entity.Post;
import com.sns.ss.entity.PostComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostCommentRepository extends JpaRepository <PostComment, Long> {

    Page<PostComment> findAllByPost(Post post, Pageable pageable);

}
