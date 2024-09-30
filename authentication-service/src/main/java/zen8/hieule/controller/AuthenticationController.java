package zen8.hieule.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import zen8.hieule.controller.request.LoginRequest;
import zen8.hieule.controller.response.LoginResponse;
import zen8.hieule.service.AuthenticationService;

import static org.springframework.http.HttpStatus.OK;

@RestController
@Slf4j
@Tag(name = "Authentication Controller")
public record AuthenticationController(AuthenticationService authenticationService) {

    @Operation(summary = "Login API", description = "Authenticate user")
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        log.info("POST /login");
        return new ResponseEntity<>(authenticationService.login(request), OK);
    }

    @Operation(summary = "Get New Access Token API", description = "Generate new access token")
    @PostMapping("/refresh-token")
    public ResponseEntity<LoginResponse> newRefreshToken(HttpServletRequest request) {
        log.info("POST /refresh-token");
        return new ResponseEntity<>(authenticationService.newRefreshToken(request), OK);
    }
}
