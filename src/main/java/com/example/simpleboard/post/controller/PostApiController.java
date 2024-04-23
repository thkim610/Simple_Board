package com.example.simpleboard.post.controller;

import com.example.simpleboard.common.Api;
import com.example.simpleboard.post.db.PostEntity;
import com.example.simpleboard.post.model.PostRequest;
import com.example.simpleboard.post.model.PostViewRequest;
import com.example.simpleboard.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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

    //게시글 조회
    //(익명 게시판이기 때문에 비밀번호를 통해 게시글을 볼 수 있음.)
    @PostMapping("/view")
    public PostEntity view(@Valid @RequestBody PostViewRequest postViewRequest){

        PostEntity entity = postService.view(postViewRequest);

        entity.getReplyList().forEach(
                it -> {}
        );

        return entity;
    }

    //게시글 목록 조회
    //페이징 처리를 위해 쿼리파라미터로 페이지와 사이즈를 받음. => 쿼리파라미터로 지정하지 않으면 기본으로 0(페이지 번호), 10(기본 크기)으로 세팅됨.
    //(page = 처음 페이지 번호, size = 기본 크기)  , direction = 정렬 방식, sort = 기준 값
    @GetMapping("/all")
    public Api<List<PostEntity>> list(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC)
                                      Pageable pageable){ //위의 정보를 Pageable에 매핑
        return postService.all(pageable);
    }

    //게시글 삭제
    // 비밀번호를 받아 일치해야 삭제가 가능.
    @PostMapping("/delete")
    public void delete(@Valid @RequestBody PostViewRequest postViewRequest){
        postService.delete(postViewRequest);
    }
}
