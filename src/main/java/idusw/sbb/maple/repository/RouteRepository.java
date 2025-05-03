package idusw.sbb.maple.repository;

import idusw.sbb.maple.domain.Route;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {

  Page<Route> findByNameContaining(String name, Pageable pageable);

  Page<Route> findByCategoryIdx_CategoryIdx(Long categoryIdx, Pageable pageable);

  Page<Route> findByNameContainingAndCategoryIdx_CategoryIdx(String name, Long categoryIdx,
      Pageable pageable);

}
