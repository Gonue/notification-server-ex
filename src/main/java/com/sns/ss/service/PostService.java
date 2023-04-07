package com.sns.ss.service;

import com.sns.ss.dto.AlarmArgs;
import com.sns.ss.dto.AlarmEvent;
import com.sns.ss.dto.PostCommentDto;
import com.sns.ss.dto.PostDto;
import com.sns.ss.entity.*;
import com.sns.ss.exception.ErrorCode;
import com.sns.ss.exception.SnsApplicationException;
import com.sns.ss.kafka.producer.AlarmProducer;
import com.sns.ss.repository.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final LikeRepository likeRepository;
    private final PostCommentRepository postCommentRepository;
    private final AlarmProducer alarmProducer;

    public PostService(PostRepository postRepository, MemberRepository memberRepository, LikeRepository likeRepository, PostCommentRepository postCommentRepository, AlarmProducer alarmProducer) {
        this.postRepository = postRepository;
        this.memberRepository = memberRepository;
        this.likeRepository = likeRepository;
        this.postCommentRepository = postCommentRepository;
        this.alarmProducer = alarmProducer;
    }

    @Transactional
    public void create(String title, String body, String email){
        Member member = getMemberOrException(email);
        postRepository.save(Post.of(title, body, member));
    }

    @Transactional
    public PostDto update(String title, String body, String email, Long postId){
        Member member = getMemberOrException(email);
        Post post = getPostOrException(postId);

        if(post.getMember() != member){
            throw new SnsApplicationException(ErrorCode.INVALID_PERMISSION, String.format("%s has no permission with %s",email, postId));
        }
        post.setTitle(title);
        post.setBody(body);
        return PostDto.from(postRepository.save(post));
    }

    public void delete(String email, Long postId){
        Member member = getMemberOrException(email);
        Post post = getPostOrException(postId);

        if(!Objects.equals(post.getMember().getMemberId(), member.getMemberId())){
            throw new SnsApplicationException(ErrorCode.INVALID_PERMISSION, String.format("user %s has no permission post", email, postId));
        }
        likeRepository.deleteAllByPost(post);
        postCommentRepository.deleteAllByPost(post);
        postRepository.delete(post);
    }

    public Page<PostDto> list(Pageable pageable) {
        return postRepository.findAll(pageable).map(PostDto::from);
    }

    public Page<PostDto> myList(String email, Pageable pageable){
        Member member = getMemberOrException(email);
        return postRepository.findAllByMember(member, pageable).map(PostDto::from);
    }

    @Transactional
    public void like(Long postId, String email){
        Post post = getPostOrException(postId);
        Member member = getMemberOrException(email);

        likeRepository.findByMemberAndPost(member, post).ifPresent(it -> {
            throw new SnsApplicationException(ErrorCode.ALREADY_LIKED, String.format("userName %s already like post %d", email,postId));
        });
        likeRepository.save(PostLike.of(member,post));
        alarmProducer.send(new AlarmEvent(post.getMember().getEmail(), Alarm.AlarmType.NEW_LIKE_ON_POST, new AlarmArgs(member.getMemberId(), post.getPostId())));
    }

    @Transactional
    public Long likeCount(Long postId){
        Post post = getPostOrException(postId);
        return likeRepository.countByPost(post);
    }

    @Transactional
    public void comment(Long postId, String comment ,String email){
        Post post = getPostOrException(postId);
        Member member = getMemberOrException(email);

        postCommentRepository.save(PostComment.of(member, post,  comment));
        alarmProducer.send(new AlarmEvent(post.getMember().getEmail(), Alarm.AlarmType.NEW_COMMENT_ON_POST, new AlarmArgs(member.getMemberId(), post.getPostId())));

    }

    public Page<PostCommentDto> commentList(Long postId, Pageable pageable){
        Post post = getPostOrException(postId);
        return postCommentRepository.findAllByPost(post, pageable).map(PostCommentDto::from);
    }



    private Post getPostOrException(Long postId){
        return postRepository.findById(postId).orElseThrow(()->
                new SnsApplicationException(ErrorCode.POST_NOT_FOUND, String.format("%s not founded", postId)));
    }

    private Member getMemberOrException(String email){
        return memberRepository.findByEmail(email).orElseThrow(() ->
                        new SnsApplicationException(ErrorCode.USER_NOT_FOUND, String.format("%s not founded", email)));
    }


}
