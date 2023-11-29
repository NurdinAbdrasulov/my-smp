package it.camerino.qrcodegenerator.service;

import it.camerino.qrcodegenerator.dto.QrCodeDto;

public interface QrCodeService {

    QrCodeDto create(QrCodeDto dto);

    QrCodeDto delete(Long id);

    QrCodeDto update(QrCodeDto dto);


}
