package org.zerock.b01.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.zerock.b01.domain.Reply;

public interface ReplyRepository extends JpaRepository<Reply,Long> {
    // where r.board.bno = :bno
    // Reply가 속한 Board 엔티티의 bno 필드가 파라미터 :bno와 같은 데이터만 조회
    @Query("select r from Reply r where r.board.bno = :bno")
    Page<Reply> listOfBoard (Long bno, Pageable pageable);
}
