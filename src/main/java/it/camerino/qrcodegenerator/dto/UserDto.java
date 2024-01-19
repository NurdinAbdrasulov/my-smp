package it.camerino.qrcodegenerator.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import it.camerino.qrcodegenerator.enums.UserRole;
import it.camerino.qrcodegenerator.enums.UserStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDto {
    Long id;
    String name;
    UserRole role;
    UserStatus status;
    String username;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime createdAt;
}
