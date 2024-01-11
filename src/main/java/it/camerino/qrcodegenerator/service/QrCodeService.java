package it.camerino.qrcodegenerator.service;

import it.camerino.qrcodegenerator.dto.QrCodeDto;
import it.camerino.qrcodegenerator.entity.QrCode;
import it.camerino.qrcodegenerator.entity.User;

import java.util.List;

public interface QrCodeService {

    List<QrCode> getAll();

    List<QrCode> getAllByUser(User currentUser);

    QrCode create(QrCodeDto dto, User currentUser);

    QrCodeDto delete(Long id);

    QrCode getByHash(String hash);
}
