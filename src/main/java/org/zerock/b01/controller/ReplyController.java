package org.zerock.b01.controller;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.b01.dto.ReplyDTO;

import java.util.HashMap;
import java.util.Map;

@RestController
@Log4j2
@RequestMapping("/replies")
public class ReplyController {

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            summary = "댓글 등록",
            description = "[POST]방식으로 댓글 등록"
    )
    public ResponseEntity<Map<String, Long>> register(@RequestBody ReplyDTO replyDTO) {
        // 넘어온 json 값
        log.info(replyDTO.toString());
        // 리턴 값
        Map<String, Long> reslultMap = Map.of("rno", 111l);
        // 상태 값 = 200 ok
        return ResponseEntity.ok(reslultMap);
    }
}
