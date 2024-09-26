package zen8.hieule.authentication_service.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import zen8.hieule.authentication_service.model.RedisToken;

@Repository
public interface TokenRepository extends CrudRepository<RedisToken, String> {
}
