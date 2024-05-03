package com.example.simpleboard.reply.service;

import com.example.simpleboard.crud.CRUDAbstractService;
import com.example.simpleboard.post.db.PostEntity;
import com.example.simpleboard.post.db.PostRepository;
import com.example.simpleboard.reply.db.ReplyEntity;
import com.example.simpleboard.reply.db.ReplyRepository;
import com.example.simpleboard.reply.model.ReplyDto;
import com.example.simpleboard.reply.model.ReplyRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReplyService extends CRUDAbstractService<ReplyDto, ReplyEntity> {
/*

    private final ReplyRepository replyRepository;
    private final PostRepository postRepository;

    public ReplyEntity create(ReplyRequest replyRequest){

        //게시글이 존재하는 여부 체크 필요.
        Optional<PostEntity> optionalPostEntity = postRepository.findById(replyRequest.getPostId());

        if(optionalPostEntity.isEmpty()){
            throw new RuntimeException("게시물이 존재하지 않습니다. : " + replyRequest.getPostId());
        }

        ReplyEntity entity = ReplyEntity.builder()
                .post(optionalPostEntity.get())
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
*/
}
