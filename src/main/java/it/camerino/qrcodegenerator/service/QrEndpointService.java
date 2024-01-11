package it.camerino.qrcodegenerator.service;

import it.camerino.qrcodegenerator.dto.QrCodeDto;
import it.camerino.qrcodegenerator.dto.QrCodeVersionDto;

import java.util.List;

public interface QrEndpointService {


    List<QrCodeDto> getAll();

    List<QrCodeDto> getAllQrsOfCurrentUser();
    QrCodeDto create(QrCodeDto dto);

    String getLinkByHash(String hash);

    QrCodeDto edit(QrCodeDto dto);

    List<QrCodeVersionDto> getOldVersionsOfQr(String hash);
}
