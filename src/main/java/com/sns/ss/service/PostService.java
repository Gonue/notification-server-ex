package com.sns.ss.service;

import com.sns.ss.dto.PostDto;
import com.sns.ss.entity.Member;
import com.sns.ss.entity.Post;
import com.sns.ss.exception.ErrorCode;
import com.sns.ss.exception.SnsApplicationException;
import com.sns.ss.repository.MemberRepository;
import com.sns.ss.repository.PostRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Objects;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    public PostService(PostRepository postRepository, MemberRepository memberRepository) {
        this.postRepository = postRepository;
        this.memberRepository = memberRepository;
    }

    @Transactional
    public void create(String title, String body, String email){
        Member member = memberRepository.findByEmail(email).orElseThrow(() ->
                new SnsApplicationException(ErrorCode.USER_NOT_FOUND, String.format("%s not founded", email)));
        postRepository.save(Post.of(title, body, member));
    }

    @Transactional
    public PostDto update(String title, String body, String email, Long postId){
        Member member = memberRepository.findByEmail(email).orElseThrow(() ->
                new SnsApplicationException(ErrorCode.USER_NOT_FOUND, String.format("%s not founded", email)));

        Post post = postRepository.findById(postId).orElseThrow(()->
                new SnsApplicationException(ErrorCode.POST_NOT_FOUND, String.format("%s not founded", postId)));

        if(post.getMember() != member){
            throw new SnsApplicationException(ErrorCode.INVALID_PERMISSION, String.format("%s has no permission with %s",email, postId));
        }
        post.setTitle(title);
        post.setBody(body);
        return PostDto.from(postRepository.save(post));
    }

    public void delete(String email, Long postId){
        Member member = memberRepository.findByEmail(email).orElseThrow(() ->
                new SnsApplicationException(ErrorCode.USER_NOT_FOUND, String.format("%s not founded", email)));
        Post post = postRepository.findById(postId).orElseThrow(()->
                new SnsApplicationException(ErrorCode.POST_NOT_FOUND, String.format("%s not founded", postId)));

        if(!Objects.equals(post.getMember().getMemberId(), member.getMemberId())){
            throw new SnsApplicationException(ErrorCode.INVALID_PERMISSION, String.format("user %s has no permission post", email, postId));
        }

        postRepository.delete(post);
    }

    public Page<PostDto> list(Pageable pageable) {
        return postRepository.findAll(pageable).map(PostDto::from);
    }

    public Page<PostDto> myList(String email, Pageable pageable){
        Member member = memberRepository.findByEmail(email).orElseThrow(() ->
                new SnsApplicationException(ErrorCode.USER_NOT_FOUND, String.format("%s not founded", email)));

        return postRepository.findAllByMember(member, pageable).map(PostDto::from);
    }
}
