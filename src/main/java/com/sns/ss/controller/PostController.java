package com.sns.ss.controller;
import com.sns.ss.dto.PostDto;
import com.sns.ss.dto.request.PostCommentRequest;
import com.sns.ss.dto.request.PostCreateRequest;
import com.sns.ss.dto.request.PostUpdateRequest;
import com.sns.ss.dto.response.PostCommentResponse;
import com.sns.ss.dto.response.PostResponse;
import com.sns.ss.dto.response.Response;
import com.sns.ss.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/v1/posts")
public class PostController {


    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }


    @PostMapping
    public Response<Void> create(@RequestBody PostCreateRequest request,
                                 Authentication authentication){

        postService.create(request.getTitle(), request.getBody(), authentication.getName());
        return Response.success();
    }

    @PutMapping("/{postId}")
    public Response<PostResponse> update(@PathVariable Long postId,
                                 @RequestBody PostUpdateRequest request,
                                 Authentication authentication){
        PostDto postDto = postService.update(request.getTitle(), request.getBody(), authentication.getName(), postId);
        return Response.success(PostResponse.from(postDto));
    }

    @DeleteMapping("/{postId}")
    public Response<Void> delete(@PathVariable Long postId,
                                 Authentication authentication){
        postService.delete(authentication.getName(), postId);
        return Response.success();
    }

    @GetMapping
    public Response<Page<PostResponse>> list(Pageable pageable){
        return Response.success(postService.list(pageable).map(PostResponse::from));
    }

    @GetMapping("/my")
    public Response<Page<PostResponse>> myList(Pageable pageable,
                               Authentication authentication){
        return Response.success(postService.myList(authentication.getName(), pageable).map(PostResponse::from));
    }

    @PostMapping("/{postId}/likes")
    public Response<Void> like(@PathVariable Long postId,
                               Authentication authentication){
        postService.like(postId,authentication.getName());
        return Response.success();

    }

    @GetMapping("/{postId}/likes")
    public Response<Long> likeCount(@PathVariable Long postId){
        return Response.success(postService.likeCount(postId));
    }

    @PostMapping("/{postId}/comments")
    public Response<Void> comment(@PathVariable Long postId,
                                  @RequestBody PostCommentRequest request,
                                  Authentication authentication){
        postService.comment(postId, request.getComment(), authentication.getName());
        return Response.success();
    }

    @GetMapping("/{postId}/comments")
    public Response<Page<PostCommentResponse>> comment(@PathVariable Long postId,
                                                       Pageable pageable){
        return Response.success(postService.commentList(postId, pageable).map(PostCommentResponse::from));
    }
}