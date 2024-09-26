package zen8.hieule.authentication_service.controller.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import zen8.hieule.authentication_service.common.Platform;

import java.io.Serializable;

@Getter
public class LoginRequest implements Serializable {

    @NotBlank(message = "username must be not null")
    private String username;

    @NotBlank(message = "username must be not blank")
    private String password;

    @NotNull(message = "username must be not null")
    private Platform platform;

    private String deviceToken;

    private String version;
}

