package org.zerock.b01.service;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.b01.dto.ReplyDTO;

@SpringBootTest
@Log4j2
public class ReplyServiceTests {
    @Autowired
    private ReplyService replyService;

    @Test
    public void testRegister(){

        ReplyDTO dto = ReplyDTO.builder()
                .replyText("댓글테스트 100")
                .replyer("정혜인")
                .bno(100l)
                .build();

        log.info(replyService.register(dto));
    }
}
