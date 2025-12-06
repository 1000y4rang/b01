package org.zerock.b01.controller;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.zerock.b01.dto.PageRequestDTO;
import org.zerock.b01.dto.PageResponseDTO;
import org.zerock.b01.dto.ReplyDTO;
import org.zerock.b01.service.ReplyService;

import java.util.HashMap;
import java.util.Map;

@RestController
@Log4j2
@RequiredArgsConstructor // final과 세트 
@RequestMapping("/replies")
public class ReplyController {

    private final ReplyService replyService;

    // 댓글 등록
    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            summary = "댓글 등록",
            description = "[POST]방식으로 댓글 등록"
    )
    public ResponseEntity<Map<String, Long>> register(@Valid @RequestBody ReplyDTO replyDTO
                                                    , BindingResult bindingResult) throws BindException {
        // 넘어온 json 값
        log.info(replyDTO.toString());
        // 에러처리
        if(bindingResult.hasErrors()) {throw new BindException(bindingResult);}
        // 답변 등록
        Long rno = replyService.register(replyDTO);
        // 리턴 값
        Map<String, Long> reslultMap = new HashMap<>();
        reslultMap.put("rno", rno);

        // 상태 값 = 200 ok
        return ResponseEntity.ok(reslultMap);
    }

    // 댓글 리스트
    @Operation(            
            summary = "댓글 목록 조회",
            description = "[GET]방식으로 댓글 목록 조회")
    @GetMapping("/list/{bno}")
    public PageResponseDTO<ReplyDTO> getList(@PathVariable("bno") Long bno
                                            , PageRequestDTO pageRequestDTO){

        PageResponseDTO<ReplyDTO> responseDTO = replyService.getListOfBoard(bno, pageRequestDTO);
        return responseDTO;
    }

    // 댓글 조회
    @Operation(
            summary = "댓글 번호 조회",
            description = "[GET]방식으로 댓글 번호 조회")
    @GetMapping("/{rno}")
    public ReplyDTO getReplyDTO(@PathVariable("rno") Long rno){

        ReplyDTO replyDTO = replyService.read(rno);
        return replyDTO;
    }
}
