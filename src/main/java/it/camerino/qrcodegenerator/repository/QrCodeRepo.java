package it.camerino.qrcodegenerator.repository;

import it.camerino.qrcodegenerator.entity.QrCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QrCodeRepo extends JpaRepository<QrCode, Long> {
}
