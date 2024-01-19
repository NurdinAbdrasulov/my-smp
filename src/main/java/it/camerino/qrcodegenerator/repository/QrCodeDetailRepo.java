package it.camerino.qrcodegenerator.repository;

import it.camerino.qrcodegenerator.entity.QrCode;
import it.camerino.qrcodegenerator.entity.QrCodeDetail;
import it.camerino.qrcodegenerator.enums.QrCodeDetailStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.List;

public interface QrCodeDetailRepo extends JpaRepository<QrCodeDetail, Long> {
    List<QrCodeDetail> findByQrCodeAndStatus(@NonNull QrCode qrCode, @NonNull QrCodeDetailStatus status);

}
