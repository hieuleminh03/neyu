package zen8.hieule.service;


import org.springframework.security.core.GrantedAuthority;
import zen8.hieule.common.TokenType;

import java.util.Collection;

public interface JwtService {

    String generateAccessToken(Long userId, String username, Collection<? extends GrantedAuthority> authorities);

    String generateRefreshToken(Long userId, String username, Collection<? extends GrantedAuthority> authorities);

    String extractUsername(String token, TokenType type);

    boolean isValid(String token, TokenType type, String username);
}
