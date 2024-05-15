package com.example.simpleboard.post.service;

import com.example.simpleboard.crud.Converter;
import com.example.simpleboard.post.db.PostEntity;
import com.example.simpleboard.post.model.PostDto;
import org.springframework.stereotype.Service;

/**
 * 데이터 변환을 위한 서비스 (Entity -> DTO)
 * 데이터 변환과정만을 담당하는 서비스
 * 데이터에 이상이 있으면 이 컨버터를 통해 찾음.
 */
@Service
public class PostConverter implements Converter<PostDto, PostEntity>{

    //postEntity를 Dto로 변환하는 메서드. (순환 문제 해결)
    public PostDto toDto(PostEntity postEntity){
        return PostDto.builder()
                .id(postEntity.getId())
                .userName(postEntity.getUserName())
                .password(postEntity.getPassword())
                .email(postEntity.getEmail())
                .status(postEntity.getStatus())
                .title(postEntity.getTitle())
                .content(postEntity.getContent())
                .postedAt(postEntity.getPostedAt())
                .boardId(postEntity.getBoard().getId())
                .build();
    }

    @Override
    public PostEntity toEntity(PostDto postDto) {
        return null;
    }
}
