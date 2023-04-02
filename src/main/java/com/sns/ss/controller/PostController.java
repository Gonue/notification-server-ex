package com.sns.ss.controller;
import com.sns.ss.dto.request.PostCreateRequest;
import com.sns.ss.dto.response.Response;
import com.sns.ss.service.PostService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


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
}