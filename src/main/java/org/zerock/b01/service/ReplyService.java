package org.zerock.b01.service;

import org.zerock.b01.dto.PageRequestDTO;
import org.zerock.b01.dto.PageResponseDTO;
import org.zerock.b01.dto.ReplyDTO;

public interface ReplyService {

    // 등록
    Long register(ReplyDTO dto);

    // 수정
    void modify(ReplyDTO dto);

    // 삭제
    void remove(ReplyDTO dto);

    // 조회
    ReplyDTO read(Long rno);

    // 댓글 목록
    PageResponseDTO<ReplyDTO> getListOfBoard(Long bno, PageRequestDTO pageRequestDTO);
}
