package org.zerock.b01.repository.search;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.zerock.b01.domain.Board;
import org.zerock.b01.dto.BoardListReplyCountDTO;

// Querydsl
public interface BoardSearch {

    // Board 테이블을 페이징으로 조회한다.
    Page<Board> search1(Pageable pageable);
    // 키워드 조회 검색
    Page<Board>  searchAll(String[] types, String keyword, Pageable pageable);

    // Board 테이블 목록 조회  + 댓글 갯수 조회
    Page<BoardListReplyCountDTO>  searchWithReplyCount(String[] types, String keyword, Pageable pageable);

    // board(+board_image) + reply
    Page<BoardListReplyCountDTO> searchAllTables(String[] types, String keyword, Pageable pageable);
}
