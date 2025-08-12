package org.zerock.b01.service;

import org.zerock.b01.dto.BoardDTO;

public interface BoardService {
    // 등록
    Long register(BoardDTO boardDTO);

    // 게시글 조회
    BoardDTO readOne(Long bno);

    // 수정
    void modify(BoardDTO boardDTO);

    // 삭제
    void delete(Long bno);
}
