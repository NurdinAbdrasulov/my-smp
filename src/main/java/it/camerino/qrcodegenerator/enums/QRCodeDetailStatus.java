package it.camerino.qrcodegenerator.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public enum QRCodeDetailStatus {
    ACTIVE("qr version is active  "),
    OLD("qr version is old "),
    ;

    String description;
}
