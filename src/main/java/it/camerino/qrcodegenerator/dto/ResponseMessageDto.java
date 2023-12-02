package it.camerino.qrcodegenerator.dto;


import it.camerino.qrcodegenerator.enums.ResultCode;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ResponseMessageDto<T> {
    T result;
    ResultCode resultCode;
    String details;
}
