package org.zerock.b01.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.io.Serializable;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "board")
public class BoardImage implements Comparable<BoardImage>{ // 이미지 정렬 인터페이스
    @Id
    private String uuid;

    private String fileName;

    private int ord;

    @ManyToOne
    private Board board;  // fk 매핑 테이블

    @Override
    // 이미지 정렬 함수
    public int compareTo(BoardImage other) {
        return this.ord - other.ord;
    }

    // 이미지 삭제용
    public void changeBoard(Board board)
    {
        this.board = board;
    }
}
