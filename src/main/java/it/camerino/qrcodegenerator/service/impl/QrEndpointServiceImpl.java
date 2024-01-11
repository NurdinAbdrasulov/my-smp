package it.camerino.qrcodegenerator.service.impl;

import it.camerino.qrcodegenerator.dto.QrCodeDto;
import it.camerino.qrcodegenerator.dto.QrCodeVersionDto;
import it.camerino.qrcodegenerator.entity.QrCode;
import it.camerino.qrcodegenerator.entity.QrCodeDetail;
import it.camerino.qrcodegenerator.entity.User;
import it.camerino.qrcodegenerator.exception.ValidationException;
import it.camerino.qrcodegenerator.service.QrCodeDetailService;
import it.camerino.qrcodegenerator.service.QrCodeService;
import it.camerino.qrcodegenerator.service.QrEndpointService;
import it.camerino.qrcodegenerator.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class QrEndpointServiceImpl implements QrEndpointService {

    QrCodeService qrCodeService;
    QrCodeDetailService qrCodeDetailService;
    UserService userService;


    @Override
    public List<QrCodeDto> getAll() {
        List<QrCodeDto> res = new ArrayList<>();

        qrCodeService.getAll().forEach(
                qrCode -> {
                    QrCodeDetail qrCodeDetail = qrCodeDetailService.getActiveByQrCode(qrCode);
                    res.add(toModel(qrCode, qrCodeDetail));
                }
        );

        return res;
    }

    @Override
    public List<QrCodeDto> getAllQrsOfCurrentUser() {
        User currentUser = userService.getCurrentUserEntity();

        return qrCodeService.getAllByUser(currentUser).stream()
                .map(qrCode -> {
                    QrCodeDetail qrCodeDetail = qrCodeDetailService.getActiveByQrCode(qrCode);

                    return toModel(qrCode, qrCodeDetail);
                })
                .toList();
    }

    @Override
    @Transactional
    public QrCodeDto create(QrCodeDto dto) {
        User currentUser = userService.getCurrentUserEntity();

        it.camerino.qrcodegenerator.entity.QrCode qrCode = qrCodeService.create(dto, currentUser);
        QrCodeDetail qrCodeDetail = qrCodeDetailService.create(dto, qrCode);

        return toModel(qrCode, qrCodeDetail);
    }

    @Override
    public String getLinkByHash(String hash) {
        QrCode qrCode = qrCodeService.getByHash(hash);

        return qrCodeDetailService.getLinkByQr(qrCode);
    }

    @Override
    public QrCodeDto edit(QrCodeDto dto) {
        User currentUser = userService.getCurrentUserEntity();
        QrCode qrCode = qrCodeService.getByHash(dto.getHash());

        if(!qrCode.getCreatedBy().getUsername().equals(currentUser.getUsername())){
            throw new ValidationException("You can only edit your QR codes");
        }

        QrCodeDetail qrCodeDetail = qrCodeDetailService.create(dto, qrCode);

        return toModel(qrCode, qrCodeDetail);
    }

    @Override
    public List<QrCodeVersionDto> getOldVersionsOfQr(String hash) {
        User currentUser = userService.getCurrentUserEntity();
        QrCode qrCode = qrCodeService.getByHash(hash);

        if(!qrCode.getCreatedBy().getUsername().equals(currentUser.getUsername())){
            throw new ValidationException("You can only edit your QR codes");
        }

        return qrCodeDetailService.getAllOldByQrCode(qrCode).stream()
                .map(detail -> toModel(detail, qrCode))
                .toList();
    }


    private QrCodeDto toModel(QrCode qrCode, QrCodeDetail qrCodeDetail) {
        return QrCodeDto.builder()
                .id(qrCode.getId())
                .hash(qrCode.getHash())
                .createdAt(qrCode.getCreatedAt())
                .createdBy(UserService.toModel(qrCode.getCreatedBy()))
                .link(qrCodeDetail.getLink())
                .color(qrCodeDetail.getColor())
                .build();
    }

    private QrCodeVersionDto toModel(QrCodeDetail qrCodeDetail, QrCode qrCode) {
        return QrCodeVersionDto.builder()
                .id(qrCode.getId())
                .hash(qrCode.getHash())
                .createdAt(qrCode.getCreatedAt())
                .createdBy(UserService.toModel(qrCode.getCreatedBy()))
                .detailId(qrCodeDetail.getId())
                .detailCreatedAt(qrCodeDetail.getCreatedAt())
                .detailStatus(qrCodeDetail.getStatus())
                .link(qrCodeDetail.getLink())
                .color(qrCodeDetail.getColor())
                .build();
    }
}
