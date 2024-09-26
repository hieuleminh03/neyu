package zen8.hieule.authentication_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zen8.hieule.authentication_service.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String userName);
}
