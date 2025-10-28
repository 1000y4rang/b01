package org.zerock.b01.repository.search;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.zerock.b01.domain.Board;
import org.zerock.b01.domain.QBoard;
import org.zerock.b01.dto.BoardListReplyCountDTO;

import java.util.LinkedList;
import java.util.List;

public class BoardSearchImpl extends QuerydslRepositorySupport implements BoardSearch {

    public BoardSearchImpl() {super(Board.class);}

    @Override
    public Page<Board> search1(Pageable pageable) {

        // 동적쿼리 Querydsl
        QBoard board = QBoard.board;
        JPQLQuery<Board> query = from(board);   // select from board
        //query.where(board.title.contains("1")); // where title like '%1%'

        // or절
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.or(board.title.contains("2"));
        booleanBuilder.or(board.content.contains("2"));
        query.where(booleanBuilder);

        // and절 gt >
        query.where(board.bno.gt(0));


        // 페이징
        this.getQuerydsl().applyPagination(pageable,query);

        List<Board> list = query.fetch();
        long count = query.fetchCount();

        return new PageImpl<Board>(list,pageable,count);
    }

    @Override
    public Page<Board> searchAll(String[] types, String keyword, Pageable pageable) {

        QBoard board = QBoard.board;
        JPQLQuery<Board> query = from(board);


        if((types!=null && types.length>0)&&keyword!=null){
            BooleanBuilder booleanBuilder = new BooleanBuilder();

            for(String type:types){
                switch (type){
                    case "t":
                        booleanBuilder.or(board.title.contains(keyword));
                        break;
                    case "c":
                        booleanBuilder.or(board.content.contains(keyword));
                        break;
                    case "w" :
                        booleanBuilder.or(board.writer.contains(keyword));
                        break;
                }
            } // end for
            query.where(booleanBuilder);
        }// end if

        query.where(board.bno.gt(0));

        // paging
        this.getQuerydsl().applyPagination(pageable,query);
        List<Board> list = query.fetch();
        long count = query.fetchCount();

        // Page<T> 타입을 return 받으려면 PageImpl<T> 클래스로 받음
        // 리스트, 페이징정보, 전체개수 파라미터가 필요함.
        return new PageImpl<Board>(list,pageable,count);
    }

    @Override
    public Page<BoardListReplyCountDTO> searchWithReplyCount(String[] types, String keyword, Pageable pageable) {
        return null;
    }
}
