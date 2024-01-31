package it.camerino.qrcodegenerator.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import it.camerino.qrcodegenerator.enums.QrCodeDetailStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class QrCodeVersionDto {
    Long id;
    String hash;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime createdAt;

    Long detailId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime detailCreatedAt;

    QrCodeDetailStatus detailStatus;
    UserDto createdBy;
    String link;
    String qrColor;
    String backgroundColor;
    String frame;
}
