package it.camerino.qrcodegenerator.service.impl;

import it.camerino.qrcodegenerator.dto.QrCodeDto;
import it.camerino.qrcodegenerator.entity.QrCode;
import it.camerino.qrcodegenerator.repository.QrCodeRepo;
import it.camerino.qrcodegenerator.service.QrCodeService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class QrCodeServiceImpl implements QrCodeService {

    QrCodeRepo repo;

    @Override
    public QrCodeDto create(QrCodeDto dto) {
        QrCode qr = new QrCode(
                null,
                UUID.randomUUID().toString(),
                LocalDateTime.now(),
                null,//todo add current user
                dto.getLink(),
                dto.getColor(),
                null
        );

        return toModel( repo.save(qr));
    }

    @Override
    public QrCodeDto delete(Long id) {
        throw new UnsupportedOperationException("Method is not implemented");
    }

    @Override
    public QrCodeDto update(QrCodeDto dto) {
        throw new UnsupportedOperationException("Method is not implemented");
    }

    @Override
    public List<QrCodeDto> getAll() {
        List<QrCodeDto> list = repo.findAll().stream().map(this::toModel).toList();

        return list;
    }

    private QrCodeDto toModel(QrCode qrCode){
        return new QrCodeDto(
                qrCode.getId(),
                qrCode.getHash(),
                qrCode.getCreatedAt(),
                qrCode.getLink(),
                qrCode.getColor()
        );
    }
}
