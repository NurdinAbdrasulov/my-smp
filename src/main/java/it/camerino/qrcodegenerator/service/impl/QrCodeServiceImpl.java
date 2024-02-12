package it.camerino.qrcodegenerator.service.impl;

import it.camerino.qrcodegenerator.dto.QrCodeDto;
import it.camerino.qrcodegenerator.entity.QrCode;
import it.camerino.qrcodegenerator.entity.User;
import it.camerino.qrcodegenerator.exception.BaseException;
import it.camerino.qrcodegenerator.repository.QrCodeRepo;
import it.camerino.qrcodegenerator.service.QrCodeService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
//@Slf4j
public class QrCodeServiceImpl implements QrCodeService {

    QrCodeRepo repo;

    @Override
    public List<QrCode> getAll() {
        return repo.findAll();
    }

    @Override
    public List<QrCode> getAllByUser(User user) {
        return repo.findAllByCreatedBy(user);
    }

    @Override
    public QrCode create(QrCodeDto dto, User currentUser) {
        QrCode qr = QrCode.builder()
                .hash(UUID.randomUUID().toString())
                .createdAt(LocalDateTime.now())
                .createdBy(currentUser)
                .build();

        return repo.save(qr);
    }

    @Override
    public QrCodeDto delete(Long id) {//todo
        throw new UnsupportedOperationException("Method is not implemented");
    }

    @Override
    public QrCode getByHash(String hash) {
        return repo.findByHash(hash).orElseThrow(
                () -> new BaseException("QR-code with the hash %s not found".formatted(hash), HttpStatus.NOT_FOUND)
        );
    }

}
