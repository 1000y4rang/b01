package org.zerock.b01.service;

import org.zerock.b01.dto.BoardDTO;
import org.zerock.b01.dto.BoardListReplyCountDTO;
import org.zerock.b01.dto.PageRequestDTO;
import org.zerock.b01.dto.PageResponseDTO;

public interface BoardService {
    // 등록
    Long register(BoardDTO boardDTO);

    // 게시글 조회
    BoardDTO readOne(Long bno);

    // 수정
    void modify(BoardDTO boardDTO);

    // 삭제
    void delete(Long bno);

    // 페이징 리스트
    //PageResponseDTO<BoardDTO> list(PageRequestDTO pageRequestDTO);

    // 리스트 + 댓글 카운트
    PageResponseDTO<BoardListReplyCountDTO> listWithReplyCount(PageRequestDTO pageRequestDTO);
}