package zen8.hieule.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import zen8.hieule.common.HashUtils;
import zen8.hieule.service.AuthorizationService;

@Service
@RequiredArgsConstructor
public class AuthorizationServiceImpl implements AuthorizationService {

    final RedisTemplate<String, String> redisTemplate;

    @Override
    public boolean isEvoked(String token) {
        return redisTemplate.opsForValue().get(HashUtils.hashToken(token)) == null;
    }
}
