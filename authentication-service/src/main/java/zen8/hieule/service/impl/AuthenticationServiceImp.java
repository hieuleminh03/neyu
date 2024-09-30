package zen8.hieule.service.impl;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import zen8.hieule.controller.request.LoginRequest;
import zen8.hieule.controller.response.LoginResponse;
import zen8.hieule.exception.InvalidDataException;
import zen8.hieule.model.User;
import zen8.hieule.repository.TokenRepository;
import zen8.hieule.repository.UserRepository;
import zen8.hieule.service.AuthenticationService;
import zen8.hieule.service.JwtService;
import zen8.hieule.common.TokenType;

import static org.springframework.http.HttpHeaders.REFERER;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthenticationServiceImp implements AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final TokenRepository tokenRepository;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    @Override
    public LoginResponse login(LoginRequest request) {

        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new AuthenticationCredentialsNotFoundException("Username not found"));

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword(), user.getAuthorities()));

        String accessToken = jwtService.generateAccessToken(user.getId(), user.getUsername(), user.getAuthorities());

        String refreshToken = jwtService.generateRefreshToken(user.getId(), user.getUsername(), user.getAuthorities());

//        List<String> roleList = user.getRoles().stream().map(role -> role.getRole().getName()).toList();
//
//        tokenRepository.save(RedisToken.builder()
//                .id(request.getUsername())
//                .accessToken(accessToken)
//                .refreshToken(refreshToken)
//                .roles(roleList.toString())
//                .build());

        return LoginResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    @Override
    public LoginResponse newRefreshToken(HttpServletRequest request) {
        // TODO: move to validator
        String refreshToken;
        try {
            refreshToken = request.getHeader(REFERER);
            if (StringUtils.isBlank(refreshToken)) {
                throw new InvalidDataException("Token must be not blank");
            }
        } catch (Exception e) {
            throw new InvalidDataException("Token must be not null");
        }


        final String userName = jwtService.extractUsername(refreshToken, TokenType.REFRESH_TOKEN);
        User user = userRepository.findByUsername(userName).orElseThrow(() -> new AuthenticationCredentialsNotFoundException("User not found"));

        if (!jwtService.isValid(refreshToken, TokenType.REFRESH_TOKEN, user.getUsername())) {
            throw new InvalidDataException("Not allow access with this token");
        }

        // generate access token
        String accessToken = jwtService.generateAccessToken(user.getId(), user.getUsername(), user.getAuthorities());

        return LoginResponse.builder()
                .accessToken(accessToken)
                .build();
    }
}
