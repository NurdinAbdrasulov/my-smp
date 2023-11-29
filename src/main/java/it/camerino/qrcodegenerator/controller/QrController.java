package it.camerino.qrcodegenerator.controller;

import it.camerino.qrcodegenerator.dto.QrCodeDto;
import it.camerino.qrcodegenerator.service.QrCodeService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("qr")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class QrController {

    QrCodeService service;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody QrCodeDto dto){
        return ResponseEntity.ok(service.create(dto));
    }

}
