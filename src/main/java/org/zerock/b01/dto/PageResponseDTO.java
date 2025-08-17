package org.zerock.b01.dto;

import lombok.Builder;

import java.util.List;

public class PageResponseDTO<E> {
    private int page;
    private int size;
    private int total;

    // 페이지별 화면 시작번호
    private int start;
    // 페이지별 화면 마지막 번호
    private int end;

    // 이전, 다음 존재여부
    private boolean prev;
    private boolean next;

    // 클래스 PageResponseDTO<E> 의 E
    private List<E> dtoList;

    // 생성자
    @Builder(builderMethodName = "withAll")
    public PageResponseDTO(PageRequestDTO pageRequestDTO, List<E> dtoList, int total) {
        if(total <= 0){
            return;
        }

        this.page = pageRequestDTO.getPage();// 9페이지라면 (페이징 9인듯)
        this.size = pageRequestDTO.getSize();
        this.total = total;
        this.dtoList = dtoList;
        this.end = (int)Math.ceil(this.page / 10.0) * 10; // 10
        this.start = this.end - 9; // 1
        int last = (int)Math.ceil(total/(double)size); // 총 192 게시글이면 20페이지가 나옴
        this.end = end > last ? end : last;
        this.prev = this.start > 1;
        this.next = total > this.end * this.size; // 192 > 10 * 10

    }
}
