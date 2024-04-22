package com.example.simpleboard.post.model;

import com.example.simpleboard.board.db.BoardEntity;
import com.example.simpleboard.reply.db.ReplyEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class) //스네이크 케이스로 받음.
public class PostDto {

    private Long id;

    private String userName;
    private String password;
    private String email;
    private String status;
    private String title;
    private String content;
    private LocalDateTime postedAt;

    //board 엔티티와 n:1 연관관계 설정
    private Long boardId;

}
