package zen8.hieule.service;

import jakarta.servlet.http.HttpServletRequest;
import zen8.hieule.controller.request.LoginRequest;
import zen8.hieule.controller.response.LoginResponse;

public interface AuthenticationService {

    LoginResponse login(LoginRequest request);

    LoginResponse newRefreshToken(HttpServletRequest request);
}
