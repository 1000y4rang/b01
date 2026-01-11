package org.zerock.b01.service;

import org.zerock.b01.domain.Board;
import org.zerock.b01.dto.BoardDTO;
import org.zerock.b01.dto.BoardListReplyCountDTO;
import org.zerock.b01.dto.PageRequestDTO;
import org.zerock.b01.dto.PageResponseDTO;

import java.util.List;
import java.util.stream.Collectors;

public interface BoardService {
    // 등록
    Long register(BoardDTO boardDTO);

    // 게시글 조회
    BoardDTO readOne(Long bno);

    // 수정
    void modify(BoardDTO boardDTO);

    // 삭제
    void delete(Long bno);

    // 페이징 리스트
    //PageResponseDTO<BoardDTO> list(PageRequestDTO pageRequestDTO);

    // 리스트 + 댓글 카운트
    PageResponseDTO<BoardListReplyCountDTO> listWithReplyCount(PageRequestDTO pageRequestDTO);

    // dto를 엔터티로 전환 메서드
    default Board dtoToEntity(BoardDTO boardDTO)
    {
        Board board = Board.builder()
                .bno(boardDTO.getBno())
                .title(boardDTO.getTitle())
                .content(boardDTO.getContent())
                .writer(boardDTO.getWriter())
                .build();

        if(boardDTO.getFileNames() != null){
            boardDTO.getFileNames().forEach(fileName -> {
                String[] arr = fileName.split("_");
                board.addImage(arr[0], arr[1]);
            });
        }
        return board;
    }

    // 엔터티를 dto로 전환
    default BoardDTO entityToDTO(Board board)
    {
        BoardDTO dto = BoardDTO.builder()
                .bno(board.getBno())
                .title(board.getTitle())
                .content(board.getContent())
                .writer(board.getWriter())
                .regDate(board.getRegDate())
                .modDate(board.getModDate())
                .build();

        List<String> fileNames = board.getImageSet().stream().sorted()
                .map(boardImage ->  boardImage.getUuid() + "_" + boardImage.getFileName())
                .collect(Collectors.toList());

        dto.setFileNames(fileNames);
        return dto;
    }
}