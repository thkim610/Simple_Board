package com.example.simpleboard.common;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Pagination {

    private Integer page; //현재 페이지
    private Integer size; //몇개가 들어있는지
    private Integer currentElements; //요소가 몇개인지
    private Integer totalPage; //총 페이지 수
    private Long totalElements; //총 요소의 개수
}
