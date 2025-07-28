package org.zerock.b01.repository;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.zerock.b01.domain.Board;

import java.awt.print.Pageable;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class BoardRepositoryTests {
    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void testInsert(){
        IntStream.range(0,100).forEach(i->{
            Board board = Board.builder()
                    .title("title..."+i)
                    .content("content..."+i)
                    .writer("user" + (i%10))
                    .build();

            // save는 데이터가 없으면 insert, 있으면 update 기능
            Board result = boardRepository.save(board);
            log.info("bno:"+result.getBno());
        });
    }

    @Test
    public void testSelect(){
        Long bno = 100L;
        // Optional<T> findById(ID id);
        Optional<Board> result = boardRepository.findById(bno);
        Board board = result.orElseThrow();
        log.info(board);
    }

    @Test
    public void testUpdate(){
        Long bno = 100L;
        // bno 100번 글 수정
        Board board =  boardRepository.findById(bno).orElseThrow();
        // 내용 수정
        board.change("title...100", "content...100");
        boardRepository.save(board);
        log.info(board);
    }

    @Test
    public void testDelete(){
        Long bno = 1L;
        boardRepository.deleteById(bno);
    }

    @Test
    public void testPaging(){

    }

}
