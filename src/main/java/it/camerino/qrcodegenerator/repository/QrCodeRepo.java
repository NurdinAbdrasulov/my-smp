package it.camerino.qrcodegenerator.repository;

import it.camerino.qrcodegenerator.entity.QrCode;
import it.camerino.qrcodegenerator.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface QrCodeRepo extends JpaRepository<QrCode, Long> {
    List<QrCode> findAllByCreatedBy(User createdBy);

    Optional<QrCode> findByHash(String hash);
}
