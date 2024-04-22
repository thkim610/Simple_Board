package com.example.simpleboard.post.service;

import com.example.simpleboard.board.db.BoardEntity;
import com.example.simpleboard.board.db.BoardRepository;
import com.example.simpleboard.post.db.PostEntity;
import com.example.simpleboard.post.db.PostRepository;
import com.example.simpleboard.post.model.PostRequest;
import com.example.simpleboard.post.model.PostViewRequest;
import com.example.simpleboard.reply.db.ReplyEntity;
import com.example.simpleboard.reply.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final BoardRepository boardRepository;
    private final ReplyService replyService;

    //게시글 작성
    public PostEntity create(PostRequest postRequest){

        //게시판이 존재하는 여부 체크 필요.
        BoardEntity boardEntity = boardRepository.findById(postRequest.getBoardId()).get(); // << 임시고정

        //빌더 패턴으로 객체 생성.
        PostEntity entity = PostEntity.builder()
                .board(boardEntity)
                .userName(postRequest.getUserName())
                .password(postRequest.getPassword())
                .email(postRequest.getEmail())
                .title(postRequest.getTitle())
                .content(postRequest.getContent())
                .status("REGISTERED")
                .postedAt(LocalDateTime.now()) //현재시간으로 작성일 입력.
                .build();

        return postRepository.save(entity);
    }

    /**
     * 게시글 조회 시,
     * 1. 게시글이 있는지 체크
     * 2. 비밀번호가 있는지 체크
     * @param postViewRequest
     * @return PostEntity
     */
    public PostEntity view(PostViewRequest postViewRequest) {
        //1. 게시글이 있는지 체크 (ID가 있고, 상태가 REGISTERED인 게시글
        return postRepository.findFirstByIdAndStatusOrderByIdDesc(postViewRequest.getPostId(), "REGISTERED")
                .map( it -> { //해당 데이터가 있는지 체크.
                    //entity가 존재할 때
                    if(!it.getPassword().equals(postViewRequest.getPassword())){
                        //패스워드가 맞지 않을 때
                        String format = "패스워드가 맞지 않습니다. %s vs %s";

                        throw new RuntimeException(String.format(format, it.getPassword(), postViewRequest.getPassword()));
                    }

                    //답변 글도 같이 적용. (출력 시, 답변 글도 보여줌.)
                    List<ReplyEntity> replyList = replyService.findByPostId(it.getId());
                    it.setReplyList(replyList);

                    return it;

                }).orElseThrow( // 게시글 자체가 존재하지 않을 떄
                        () -> {
                            return new RuntimeException("해당 게시글이 존재하지 않습니다. : " + postViewRequest.getPostId());
                        }
                );
    }

    //게시글 목록 조회
    public List<PostEntity> all() {
        return postRepository.findAllByStatusOrderByIdDesc("REGISTERED");
    }

    /**
     * 게시글 삭제 시,
     * 1. 게시글이 있는지 체크
     * 2. 비밀번호가 있는지 체크
     * @param postViewRequest
     */
    //게시글 삭제
    public void delete(PostViewRequest postViewRequest) {
        //1. 게시글이 있는지 체크
        postRepository.findById(postViewRequest.getPostId())
                .map( it -> { //해당 데이터가 있는지 체크.
                    //entity가 존재할 때
                    if(!it.getPassword().equals(postViewRequest.getPassword())){
                        //패스워드가 맞지 않을 때
                        String format = "패스워드가 맞지 않습니다. %s vs %s";

                        throw new RuntimeException(String.format(format, it.getPassword(), postViewRequest.getPassword()));
                    }

                    it.setStatus("UNREGISTERED");
                    postRepository.save(it);

                    return it;

                }).orElseThrow( // 게시글 자체가 존재하지 않을 떄
                        () -> {
                            return new RuntimeException("해당 게시글이 존재하지 않습니다. : " + postViewRequest.getPostId());
                        }
                );
    }
}
