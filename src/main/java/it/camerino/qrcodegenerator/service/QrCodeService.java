package it.camerino.qrcodegenerator.service;

import it.camerino.qrcodegenerator.dto.LinkDto;
import it.camerino.qrcodegenerator.dto.QrCodeDto;

import java.util.List;

public interface QrCodeService {

    QrCodeDto create(QrCodeDto dto);

    QrCodeDto delete(Long id);

    QrCodeDto update(QrCodeDto dto);

    List<QrCodeDto> getAll();

    List<QrCodeDto> getAllQrsOfCurrentUser();

    LinkDto getLinkByHas(String hash);
}
