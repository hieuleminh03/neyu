package zen8.hieule.authentication_service.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Gender {

    @JsonProperty("male")
    MALE("male"),

    @JsonProperty("female")
    FEMALE("female"),

    @JsonProperty("other")
    OTHER("other");

    private final String value;
}
