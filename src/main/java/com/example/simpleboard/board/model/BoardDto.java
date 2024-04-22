package com.example.simpleboard.board.model;

import com.example.simpleboard.post.model.PostDto;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;
import java.util.List;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class) //Json key 인자를 스네이크 표기법으로 작성.
public class BoardDto {
    private Long id;
    private String boardName;
    private String status;

    private List<PostDto> postlist = List.of();
}
