package org.zerock.b01.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.zerock.b01.domain.Reply;
import org.zerock.b01.dto.PageRequestDTO;
import org.zerock.b01.dto.PageResponseDTO;
import org.zerock.b01.dto.ReplyDTO;
import org.zerock.b01.repository.ReplyRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService {

    private final ReplyRepository replyRepository;
    private final ModelMapper modelMapper;

    @Override
    public Long register(ReplyDTO dto) {
        Reply reply = modelMapper.map(dto, Reply.class);
        Long rno = replyRepository.save(reply).getRno();

        return rno;
    }

    @Override
    public void modify(ReplyDTO dto) {
        // 값이 있는지 체크
        // 값이 있을 수도 있고 없을 수도 있음. 있으면 Optional 안에 T 타입 객체가 들어 있음
        Optional<Reply> replyOptional = replyRepository.findById(dto.getRno());
        Reply reply = replyOptional.orElseThrow();
        // 넘어온 값 중에서 text만 변경 후 저장
        reply.changeText(dto.getReplyText());
        replyRepository.save(reply);
    }

    @Override
    public void remove(ReplyDTO dto) {
        replyRepository.deleteById(dto.getRno());
    }

    @Override
    public ReplyDTO read(Long rno) {
        // 값이 있는지 체크
        Optional<Reply> replyOptional = replyRepository.findById(rno);
        Reply reply = replyOptional.orElseThrow();
        return modelMapper.map(reply, ReplyDTO.class);
    }

    // 댓글 페이징 리스트
    @Override
    public PageResponseDTO<ReplyDTO> getListOfBoard(Long bno, PageRequestDTO pageRequestDTO) {
        // 페이징(시작 row number(index), 가져올 개수, 정렬)
        Pageable pageable = PageRequest.of(pageRequestDTO.getPage() <= 0 ? 0 : pageRequestDTO.getPage()-1
                , pageRequestDTO.getSize()
                , Sort.by("rno").ascending());

        // 댓글 리스트 @Query("select r from Reply r where r.board.bno = :bno")
        Page<Reply> result = replyRepository.listOfBoard(bno, pageable);

        // ReplyDto로 변환 (modelMapper)
        List<ReplyDTO> replyList = result.getContent().stream().map(reply -> modelMapper.map(reply, ReplyDTO.class)).collect(Collectors.toList());

        // PageResponseDTO로 변환
        return PageResponseDTO.<ReplyDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(replyList)
                .total((int)result.getTotalElements())
                .build();
    }
}
