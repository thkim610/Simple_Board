package com.example.simpleboard.board.db;

import com.example.simpleboard.post.db.PostEntity;
import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity(name = "board")
public class BoardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String boardName;
    private String status;

    //post 엔티티와 1:n 연관관계 설정
    @OneToMany(mappedBy = "board")
    @Builder.Default //빌더 패턴으로 생성할 때 해당 필드가 누락되지 않고 같이 생성될 수 있도록 설정해줌.
    private List<PostEntity> postlist = List.of();
}
