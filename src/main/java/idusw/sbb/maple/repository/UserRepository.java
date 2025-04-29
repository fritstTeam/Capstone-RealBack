package idusw.sbb.maple.repository;

import idusw.sbb.maple.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
