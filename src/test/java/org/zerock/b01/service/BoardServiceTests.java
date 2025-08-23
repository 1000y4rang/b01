package org.zerock.b01.service;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.b01.dto.BoardDTO;
import org.zerock.b01.dto.PageRequestDTO;
import org.zerock.b01.dto.PageResponseDTO;

@SpringBootTest
@Log4j2
public class BoardServiceTests {
    @Autowired
    BoardService boardService;

    @Test
    public void testRegister(){
        BoardDTO boardDTO = BoardDTO.builder()
                        .title("게시글1")
                        .content("내용1")
                        .writer("user01")
                        .build();

        Long bno = boardService.register(boardDTO);
        log.info(bno);
    }

    @Test
    public void testModify(){
        BoardDTO boardDTO = BoardDTO.builder()
                .bno(101L)
                .title("신규 글")
                .content("수정하다...")
                .build();
        boardService.modify(boardDTO);
    }

    @Test
    public void testDelete(){
        Long bno = 101L;
        boardService.delete(bno);
    }

    @Test
    public void testList(){
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                        .type("tcw")
                        .keyword("1")
                        .page(3)
                        .size(10)
                        .build();

        PageResponseDTO<BoardDTO> dtoList = boardService.list(pageRequestDTO);
        log.info(dtoList);
    }

}
