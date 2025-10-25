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

    @Test
    public void testInsert(){
        // 실제 있는 게시글
        Long bno = 300L;
        Board board = Board.builder().bno(bno).build();
        Reply reply = Reply.builder().board(board)
                .replyText("댓글테스트")
                .replyer("정혜인")
                .build();

        // 게시글 100번에 댓글 저장
        replyRepository.save(reply);
    }
}
