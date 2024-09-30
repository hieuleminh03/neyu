package zen8.hieule.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import zen8.hieule.model.RedisToken;

@Repository
public interface TokenRepository extends CrudRepository<RedisToken, String> {
}
