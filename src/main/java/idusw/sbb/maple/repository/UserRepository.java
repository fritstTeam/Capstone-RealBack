package idusw.sbb.maple.repository;

import idusw.sbb.maple.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  User findByUserId(String userId);

  boolean existsByUserId(String userId);

  @Override
  <S extends User> S save(S entity); // insert or update

  @Override
  void deleteById(Long userId);
}
