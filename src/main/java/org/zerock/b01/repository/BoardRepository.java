package org.zerock.b01.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.zerock.b01.domain.Board;
import org.zerock.b01.repository.search.BoardSearch;

import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board,Long>, BoardSearch {

    // 사용자가 직접 만든 쿼리
    // @EntityGraph : 지연 로딩(LAZY)으로 설정된 연관 엔티티를 즉시 로딩(EAGER처럼)하도록 강제
    @EntityGraph(attributePaths = {"imageSet"})
    @Query("select b from Board b where b.bno = :bno")
    Optional<Board> findByIdWithImages(Long bno);

    // Optional
    // 값이 있을 수도 있고, 없을 수도 있음을 명시적으로 표현하는 래퍼 객체입니다.
    //값이 있으면 → Optional.of(board)
    //값이 없으면 → Optional.empty()

}
