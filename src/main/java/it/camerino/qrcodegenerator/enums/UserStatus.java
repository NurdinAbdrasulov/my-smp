package it.camerino.qrcodegenerator.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum UserStatus {
    ACTIVE("User is active  "),
    BLOCKED("User was blocked "),
    ;

    String description;
}
