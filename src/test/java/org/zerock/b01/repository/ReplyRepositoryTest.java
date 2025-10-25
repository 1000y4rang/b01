package org.zerock.b01.repository;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.b01.domain.Board;
import org.zerock.b01.domain.Reply;

@SpringBootTest
@Log4j2
public class ReplyRepositoryTest {
    @Autowired
    ReplyRepository replyRepository;
    @Autowired
    BoardRepository boardRepository;

    @Test
    public void testInsert(){
        // 실제 있는 게시글
        Long bno = 200L;
        Board board = boardRepository.findById(bno)
                .orElseThrow(() -> new IllegalArgumentException("게시글 없음: " + bno));

        Reply reply = Reply.builder()
                .board(board)
                .replyText("댓글테스트")
                .replyer("정혜인")
                .build();

        replyRepository.save(reply);
    }
}
