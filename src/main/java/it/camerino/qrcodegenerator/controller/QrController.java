package it.camerino.qrcodegenerator.controller;

import it.camerino.qrcodegenerator.dto.LinkDto;
import it.camerino.qrcodegenerator.dto.QrCodeDto;
import it.camerino.qrcodegenerator.service.QrCodeService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("qr")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class QrController {

    QrCodeService service;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody QrCodeDto dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @PostMapping("edit")
    public ResponseEntity<?> edit(@RequestBody QrCodeDto dto) {
        return ResponseEntity.ok(service.update(dto));
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("link")
    public ResponseEntity<?> getLink(String hash) {
        return ResponseEntity.ok(service.getLinkByHas(hash));
    }

    @GetMapping("redirect")
    void redirect(HttpServletResponse response, String hash) throws IOException {
        LinkDto linkByHas = service.getLinkByHas(hash);
        response.sendRedirect(linkByHas.getLink());
    }

}
