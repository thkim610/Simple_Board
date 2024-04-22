package com.example.simpleboard.post.db;

import com.example.simpleboard.board.db.BoardEntity;
import com.example.simpleboard.reply.db.ReplyEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity(name = "post")
public class PostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userName;
    private String password;
    private String email;
    private String status;
    private String title;
    @Column(columnDefinition = "TEXT")
    private String content;
    private LocalDateTime postedAt;

    //board 엔티티와 n:1 연관관계 설정
    @ManyToOne
    @JsonIgnore //Json 객체를 생성할때 게시판과 게시글을 계속 무한 반복하며 생성되는 것을 방지. (게시글 객체 생성 시, 게시판의 내용 생성X)
    @ToString.Exclude //toString도 무한 반복되어 로그를 찍을 때 에러가 발생하므로 그것을 방지.
    private BoardEntity board; //'_id'를 제외한 컬럼명으로 매칭시켜야 한다. (board + _id => board_id)

    //답변 글 목록
    //reply 엔티티와 1:n 연관관계 설정
    @OneToMany(mappedBy = "post")
    @Builder.Default
    private List<ReplyEntity> replyList = new ArrayList<>(); //답변이 비어있는 것을 기본값으로 설정.
}
