package com.example.simpleboard.reply.db;

import com.example.simpleboard.post.db.PostEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity(name = "reply")
public class ReplyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //post 엔티티와 n:1 연관관계 설정
    @ManyToOne
    @JsonIgnore //Json 객체를 생성할때 게시글과 댓글을 계속 무한 반복하며 생성되는 것을 방지. (댓글 객체 생성 시, 게시글의 내용 생성X)
    @ToString.Exclude //toString도 무한 반복되어 로그를 찍을 때 에러가 발생하므로 그것을 방지.
    private PostEntity post; //(post + _id => post_id)

    private String userName;
    private String password;
    private String status;
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;
    private LocalDateTime repliedAt;
}
