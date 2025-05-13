package idusw.sbb.maple.repository;

import idusw.sbb.maple.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {

}
