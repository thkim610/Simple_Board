package com.example.simpleboard.board.service;

import com.example.simpleboard.board.db.BoardEntity;
import com.example.simpleboard.board.db.BoardRepository;
import com.example.simpleboard.board.model.BoardDto;
import com.example.simpleboard.crud.Converter;
import com.example.simpleboard.post.db.PostEntity;
import com.example.simpleboard.post.db.PostRepository;
import com.example.simpleboard.post.model.PostDto;
import com.example.simpleboard.post.service.PostConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
/**
 * 데이터 변환을 위한 서비스 (Entity -> DTO)
 * 데이터 변환과정만을 담당하는 서비스
 * 데이터에 이상이 있으면 이 컨버터를 통해 찾음.
 */
@Service
@RequiredArgsConstructor
public class BoardConverter implements Converter<BoardDto, BoardEntity> {

    private final PostConverter postConverter;

    @Override
    public BoardDto toDto(BoardEntity boardEntity) {

        //postConverter를 통해 postList를 Dto로 변환해서 가져옴.
        List<PostDto> postList = boardEntity.getPostlist().stream()
                .map(postEntity -> {
                    return postConverter.toDto(postEntity);
                })
                .collect(Collectors.toList());

        return BoardDto.builder()
                .id(boardEntity.getId())
                .status(boardEntity.getStatus())
                .boardName(boardEntity.getBoardName())
                .postlist(postList)
                .build();
    }

    @Override
    public BoardEntity toEntity(BoardDto boardDto) {

        return BoardEntity.builder()
                .id(boardDto.getId())
                .status((boardDto.getStatus() != null) ? boardDto.getStatus() : "REGISTERED")
                .boardName(boardDto.getBoardName())
                .build();
    }

//    private final PostConverter postConverter;
//
//    //boardEntity를 Dto로 변환하는 메서드. (순환 문제 해결)
//    public BoardDto toDto(BoardEntity boardEntity){
//
//        //postConverter를 통해 postList를 Dto로 변환해서 가져옴.
//        List<PostDto> postList = boardEntity.getPostlist().stream()
//                .map(postEntity -> {
//                    return postConverter.toDto(postEntity);
//                })
//                .collect(Collectors.toList());
//
//        return BoardDto.builder()
//                .id(boardEntity.getId())
//                .boardName(boardEntity.getBoardName())
//                .status(boardEntity.getStatus())
//                .postlist(postList)
//                .build();
//    }


}
