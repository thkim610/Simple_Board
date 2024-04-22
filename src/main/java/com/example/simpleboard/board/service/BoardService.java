package com.example.simpleboard.board.service;

import com.example.simpleboard.board.db.BoardEntity;
import com.example.simpleboard.board.db.BoardRepository;
import com.example.simpleboard.board.model.BoardDto;
import com.example.simpleboard.board.model.BoardRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final BoardConverter boardConverter;

    //게시판 생성
    public BoardDto create(BoardRequest boardRequest){

        //빌더 패턴으로 객체 생성.
        BoardEntity entity = BoardEntity.builder()
                .boardName(boardRequest.getBoardName())
                .status("REGISTERED")
                .build();

        //JPA를 통해 엔티티 저장.
        BoardEntity saveEntity = boardRepository.save(entity);

        return boardConverter.toDto(saveEntity);//엔터티를 dto로 변환.
    }

    //게시판 조회
    public BoardDto view(Long id) {

        BoardEntity boardEntity = boardRepository.findById(id).get();

        return boardConverter.toDto(boardEntity); //엔터티를 dto로 변환하여 반환.
    }
}
