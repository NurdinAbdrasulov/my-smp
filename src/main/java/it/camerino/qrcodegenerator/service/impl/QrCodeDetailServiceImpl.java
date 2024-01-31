package it.camerino.qrcodegenerator.service.impl;

import it.camerino.qrcodegenerator.dto.QrCodeDto;
import it.camerino.qrcodegenerator.entity.QrCode;
import it.camerino.qrcodegenerator.entity.QrCodeDetail;
import it.camerino.qrcodegenerator.enums.QrCodeDetailStatus;
import it.camerino.qrcodegenerator.exception.BaseException;
import it.camerino.qrcodegenerator.repository.QrCodeDetailRepo;
import it.camerino.qrcodegenerator.service.QrCodeDetailService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class QrCodeDetailServiceImpl implements QrCodeDetailService {

    QrCodeDetailRepo repo;

    @Override
    public QrCodeDetail create(QrCodeDto dto, QrCode qrCode) {
        getActiveDetailByQrCode(qrCode)
                .ifPresent(
                        qrCodeDetail -> {
                            qrCodeDetail.setStatus(QrCodeDetailStatus.OLD);

                            repo.save(qrCodeDetail);
                        }
                );

        QrCodeDetail detail = QrCodeDetail.builder()
                .qrCode(qrCode)
                .link(dto.getLink())
                .qrColor(dto.getQrColor())
                .backgroundColor(dto.getBackgroundColor())
                .frame(dto.getFrame())
                .scanNumber(BigInteger.ZERO)
                .status(QrCodeDetailStatus.ACTIVE)
                .createdAt(LocalDateTime.now())
                .build();

        return repo.save(detail);
    }

    @Override
    public QrCodeDetail getActiveByQrCode(it.camerino.qrcodegenerator.entity.QrCode qrCode) {
        return getActiveDetailByQrCode(qrCode).orElseThrow(
                () -> new BaseException("Detail of qr with id %S not fount".formatted(qrCode.getId()), HttpStatus.NOT_FOUND)
        );
    }

    @Override
    public String getLinkByQr(QrCode qrCode) {
        QrCodeDetail detail = getActiveByQrCode(qrCode);
        detail.setScanNumber(detail.getScanNumber().add(BigInteger.ONE));
        repo.save(detail);

        return detail.getLink();
    }

    @Override
    public List<QrCodeDetail> getAllOldByQrCode(QrCode qrCode) {
        return repo.findByQrCodeAndStatus(qrCode, QrCodeDetailStatus.OLD);
    }


    private Optional<QrCodeDetail> getActiveDetailByQrCode(QrCode qrCode) {
        List<QrCodeDetail> details = repo.findByQrCodeAndStatus(qrCode, QrCodeDetailStatus.ACTIVE);

        if (details.size() > 1) {
            throw new BaseException("There are more than 1 details of qr with id %s".formatted(qrCode.getId()), HttpStatus.INTERNAL_SERVER_ERROR);

        }

        return details.isEmpty() ? Optional.empty() : Optional.of(details.get(0));
    }
}
