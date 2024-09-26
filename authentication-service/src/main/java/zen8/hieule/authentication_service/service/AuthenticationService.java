package zen8.hieule.authentication_service.service;

import jakarta.servlet.http.HttpServletRequest;
import zen8.hieule.authentication_service.controller.request.LoginRequest;
import zen8.hieule.authentication_service.controller.response.TokenResponse;

public interface AuthenticationService {

    TokenResponse createAccessToken(LoginRequest request);

    TokenResponse createRefreshToken(HttpServletRequest request);
}
