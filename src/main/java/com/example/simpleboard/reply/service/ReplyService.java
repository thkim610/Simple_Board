package com.example.simpleboard.reply.service;

import com.example.simpleboard.post.db.PostEntity;
import com.example.simpleboard.reply.db.ReplyEntity;
import com.example.simpleboard.reply.db.ReplyRepository;
import com.example.simpleboard.reply.model.ReplyRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReplyService {

    private final ReplyRepository replyRepository;

    public ReplyEntity create(ReplyRequest replyRequest){

        ReplyEntity entity = ReplyEntity.builder()
                .postId(replyRequest.getPostId())
                .userName(replyRequest.getUserName())
                .password(replyRequest.getPassword())
                .status("REGISTERED")
                .title(replyRequest.getTitle())
                .content(replyRequest.getContent())
                .repliedAt(LocalDateTime.now())
                .build();

        return replyRepository.save(entity);
    }

    //해당 게시글의 모든 답변 조회.
    public List<ReplyEntity> findByPostId(Long postId){

        return replyRepository.findAllByPostIdAndStatusOrderByIdDesc(postId, "REGISTERED");
    }
}
