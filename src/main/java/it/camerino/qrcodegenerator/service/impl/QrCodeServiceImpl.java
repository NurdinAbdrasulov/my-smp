package it.camerino.qrcodegenerator.service.impl;

import it.camerino.qrcodegenerator.dto.LinkDto;
import it.camerino.qrcodegenerator.dto.QrCodeDto;
import it.camerino.qrcodegenerator.entity.QrCode;
import it.camerino.qrcodegenerator.entity.User;
import it.camerino.qrcodegenerator.exception.BaseException;
import it.camerino.qrcodegenerator.exception.ValidationException;
import it.camerino.qrcodegenerator.repository.QrCodeRepo;
import it.camerino.qrcodegenerator.service.QrCodeService;
import it.camerino.qrcodegenerator.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class QrCodeServiceImpl implements QrCodeService {

    QrCodeRepo repo;
    UserService userService;

    @Override
    public QrCodeDto create(QrCodeDto dto) {
        User currentUser = userService.getCurrentUserEntity();

        QrCode qr = QrCode.builder()
                .hash(UUID.randomUUID().toString())
                .createdAt(LocalDateTime.now())
                .createdBy(currentUser)
                .link(dto.getLink())
                .color(dto.getColor())
                .scanNumber(BigInteger.ZERO)
                .build();

        return toModel(repo.save(qr));
    }

    @Override
    public QrCodeDto delete(Long id) {
        throw new UnsupportedOperationException("Method is not implemented");
    }

    @Override
    public QrCodeDto update(QrCodeDto dto) {
        if (dto.getId() == null) {
            throw new ValidationException("When editing, Id must not be null");
        }

        QrCode entity = repo.findById(dto.getId())
                .map(
                        qrCode -> {
                            qrCode.setColor(dto.getColor());
                            qrCode.setLink(dto.getLink());
                            qrCode.setUpdatedAt(LocalDateTime.now());

                            return qrCode;
                        }
                )
                .orElseThrow(
                        () -> new BaseException("The QR-code with id %s not found".formatted(dto.getId()), HttpStatus.NOT_FOUND)
                );

        return toModel(repo.save(entity));
    }

    @Override
    public List<QrCodeDto> getAll() {
        return repo.findAll().stream().map(this::toModel).toList();
    }

    @Override
    public List<QrCodeDto> getAllQrsOfCurrentUser() {
        User currentUser = userService.getCurrentUserEntity();

        return repo.findAllByCreatedBy(currentUser).stream().map(this::toModel).toList();
    }

    @Override
    public LinkDto getLinkByHas(String hash) {
        QrCode qr = repo.findByHash(hash).orElseThrow(
                () -> new BaseException("QR-code with the hash %s not found".formatted(hash), HttpStatus.NOT_FOUND)
        );
        qr.setScanNumber(qr.getScanNumber().add(BigInteger.ONE));
        repo.save(qr);

        return new LinkDto(qr.getHash(), qr.getLink());
    }

    private QrCodeDto toModel(QrCode qrCode) {
        return QrCodeDto.builder()
                .id(qrCode.getId())
                .hash(qrCode.getHash())
                .createdAt( qrCode.getCreatedAt())
                .createdBy(UserService.toModel(qrCode.getCreatedBy()))
                .link(qrCode.getLink())
                .color(qrCode.getColor())
                .build();
    }
}
