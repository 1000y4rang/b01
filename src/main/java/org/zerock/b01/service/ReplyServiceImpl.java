package org.zerock.b01.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.zerock.b01.domain.Reply;
import org.zerock.b01.dto.ReplyDTO;
import org.zerock.b01.repository.ReplyRepository;

import java.util.Optional;

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
}
