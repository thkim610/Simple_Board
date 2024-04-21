package com.example.simpleboard.post.db;

import com.example.simpleboard.reply.db.ReplyEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
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

    private Long boardId;
    private String userName;
    private String password;
    private String email;
    private String status;
    private String title;
    @Column(columnDefinition = "TEXT")
    private String content;
    private LocalDateTime postedAt;

    //답변 글 목록
    @Transient //게시글 db의 컬럼으로 인식하지 않도록 해주는 어노테이션
    private List<ReplyEntity> replyList = List.of(); //답변이 비어있는 것을 기본값으로 설정.
}
