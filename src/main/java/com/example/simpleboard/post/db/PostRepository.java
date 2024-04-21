package com.example.simpleboard.post.db;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<PostEntity, Long> {

    //게시글 조회 (상태가 조회가능한 경우)
    //select * from post where id = ? and status = ? order by id desc limit 1
    Optional<PostEntity> findFirstByIdAndStatusOrderByIdDesc(Long id, String status);

    //게시글 목록 조회 (상태가 조회가능한 경우)
    //select * from post where status = ? order by id desc
    List<PostEntity> findAllByStatusOrderByIdDesc(String status);
}
