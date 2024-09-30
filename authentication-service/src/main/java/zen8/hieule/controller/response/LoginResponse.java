package zen8.hieule.controller.response;

import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Getter
@Builder
public class LoginResponse implements Serializable {
    private String accessToken;
    private String refreshToken;
}
