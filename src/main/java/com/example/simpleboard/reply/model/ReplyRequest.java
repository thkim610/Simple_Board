package com.example.simpleboard.reply.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class) //스네이크 케이스로 받음.
public class ReplyRequest {

    @NotNull
    private Long postId;

    @NotBlank
    private String userName;

    @NotBlank
    @Size(min = 4, max = 4) //비밀번호는 4자리
    private String password;

    @NotBlank
    private String title;

    @NotBlank
    private String content;
}
