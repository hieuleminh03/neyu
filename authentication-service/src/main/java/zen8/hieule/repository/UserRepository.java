package zen8.hieule.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zen8.hieule.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String userName);

}