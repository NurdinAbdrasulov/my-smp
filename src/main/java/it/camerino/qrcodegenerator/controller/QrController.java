package it.camerino.qrcodegenerator.controller;

import it.camerino.qrcodegenerator.dto.QrCodeDto;
import it.camerino.qrcodegenerator.exception.BaseException;
import it.camerino.qrcodegenerator.service.QrEndpointService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("qr")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class QrController {

    QrEndpointService service;

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("current-user")
    public ResponseEntity<?> getCurrentUserQrs() {
        return ResponseEntity.ok(service.getAllQrsOfCurrentUser());
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody QrCodeDto dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @PostMapping("edit")
    public ResponseEntity<?> edit(@RequestBody QrCodeDto dto) {
        return ResponseEntity.ok(service.edit(dto));
    }

    @GetMapping("redirect")
    void redirect(HttpServletResponse response, String hash) {
        try {
            response.sendRedirect(service.getLinkByHash(hash));
        } catch (IOException e) {
            throw new BaseException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("old-versions")
    public ResponseEntity<?> getOldVersionsOfQr(String hash) {
        return ResponseEntity.ok(service.getOldVersionsOfQr(hash));

    }

}
