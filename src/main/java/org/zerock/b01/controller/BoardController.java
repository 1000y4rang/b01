package org.zerock.b01.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.b01.dto.BoardDTO;
import org.zerock.b01.dto.PageRequestDTO;
import org.zerock.b01.dto.PageResponseDTO;
import org.zerock.b01.service.BoardService;


@Controller
@Log4j2
@RequestMapping("/board")
@RequiredArgsConstructor    // final 객체를 생성자에서 주입받음. @Autowired : 순환참조가 일어날 수 있기 때문
public class BoardController {
    private final BoardService boardService;    // @RequiredArgsConstructor를 선언해서
                                                // 생성자 public BoardController(BoardService boardService)를 주입받는다.
                                                // 그래서 boardService.함수()를 바로 쓸 수 있나보다.

    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model){
        PageResponseDTO<BoardDTO> responseDTO = boardService.list(pageRequestDTO);
        model.addAttribute("responseDTO", responseDTO);
    }





}
