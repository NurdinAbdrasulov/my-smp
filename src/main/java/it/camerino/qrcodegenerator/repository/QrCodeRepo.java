package it.camerino.qrcodegenerator.repository;

import it.camerino.qrcodegenerator.entity.QrCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface QrCodeRepo extends JpaRepository<QrCode, Long> {
    Optional<QrCode> findByHash(String hash);
}
