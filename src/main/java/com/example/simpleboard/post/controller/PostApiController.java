package com.example.simpleboard.post.controller;

import com.example.simpleboard.post.db.PostEntity;
import com.example.simpleboard.post.model.PostRequest;
import com.example.simpleboard.post.model.PostViewRequest;
import com.example.simpleboard.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/post")
@RequiredArgsConstructor
public class PostApiController {

    private final PostService postService;

    //게시글 작성
    @PostMapping("")
    public PostEntity create(@Valid @RequestBody PostRequest postRequest){
        return postService.create(postRequest);
    }

    //게시글 조회 (익명 게시판이기 때문에 비밀번호를 통해 게시글을 볼 수 있음.)
    @PostMapping("/view")
    public PostEntity view(@Valid @RequestBody PostViewRequest postViewRequest){
        return postService.view(postViewRequest);
    }

    //게시글 목록 조회
    public List<PostEntity> list(){

    }

    //게시글 삭제
    public void delete(){

    }
}
