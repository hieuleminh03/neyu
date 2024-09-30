package zen8.hieule.service;

import org.springframework.stereotype.Service;

@Service
public interface AuthorizationService {
    boolean isEvoked(String token);
}