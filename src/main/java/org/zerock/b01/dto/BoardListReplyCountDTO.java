package org.zerock.b01.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BoardListReplyCountDTO {

    private Long bno;

    @NotEmpty
    @Size(min=3, max=100)
    private String title;

    @NotEmpty
    private String content;

    @NotEmpty
    private String writer;
    private LocalDateTime regDate;
    private LocalDateTime modDate;

    private Long replyCount;
}
