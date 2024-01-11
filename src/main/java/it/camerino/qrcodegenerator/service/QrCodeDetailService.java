package it.camerino.qrcodegenerator.service;

import it.camerino.qrcodegenerator.dto.QrCodeDto;
import it.camerino.qrcodegenerator.entity.QrCode;
import it.camerino.qrcodegenerator.entity.QrCodeDetail;

import java.util.List;

public interface QrCodeDetailService {

    QrCodeDetail create(QrCodeDto dto, QrCode qrCode);

    QrCodeDetail getActiveByQrCode(QrCode qrCode);

    String getLinkByQr(QrCode qrCode);

    List<QrCodeDetail> getAllOldByQrCode(QrCode qrCode);
}
