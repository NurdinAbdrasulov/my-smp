package it.camerino.qrcodegenerator.service;

import it.camerino.qrcodegenerator.dto.Dto;
import it.camerino.qrcodegenerator.dto.QrCodeDto;

import java.util.List;

public interface QrCodeService {

    QrCodeDto create(QrCodeDto dto);

    QrCodeDto delete(Long id);

    QrCodeDto update(QrCodeDto dto);

    List<QrCodeDto> getAll();

    Dto getLinkByHas(String hash);
}
