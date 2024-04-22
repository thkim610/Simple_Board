package com.example.simpleboard.board.controller;

import com.example.simpleboard.board.db.BoardEntity;
import com.example.simpleboard.board.db.BoardRepository;
import com.example.simpleboard.board.model.BoardDto;
import com.example.simpleboard.board.model.BoardRequest;
import com.example.simpleboard.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board")
public class BoardApiController {

    private final BoardService boardService;

    //게시판 생성
    @PostMapping("")
    public BoardDto create(@Valid @RequestBody BoardRequest boardRequest){
        return boardService.create(boardRequest);
    }

    //게시판 조회
    @GetMapping("/id/{id}")
    public BoardDto view(@PathVariable Long id){
        BoardDto entity = boardService.view(id);

        log.info("result : {}", entity);
        return entity;
    }

}
