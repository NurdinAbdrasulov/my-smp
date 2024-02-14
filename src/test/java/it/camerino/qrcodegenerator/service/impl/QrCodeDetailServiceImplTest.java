package it.camerino.qrcodegenerator.service.impl;
import it.camerino.qrcodegenerator.dto.QrCodeDto;
import it.camerino.qrcodegenerator.entity.QrCode;
import it.camerino.qrcodegenerator.entity.QrCodeDetail;
import it.camerino.qrcodegenerator.entity.User;
import it.camerino.qrcodegenerator.enums.QrCodeDetailStatus;
import it.camerino.qrcodegenerator.exception.BaseException;
import it.camerino.qrcodegenerator.repository.QrCodeDetailRepo;
import it.camerino.qrcodegenerator.repository.QrCodeRepo;
import it.camerino.qrcodegenerator.repository.UserRepo;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.mockito.Mockito;
import org.mockito.internal.matchers.Equals;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.junit.jupiter.api.condition.EnabledOnOs;
import org.springframework.http.HttpStatus;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public class QrCodeDetailServiceImplTest {

    @Test
    public void testGetActiveByQrCode_Success() {
        // Przygotowanie danych testowych
        QrCode qrCode = new QrCode();
        qrCode.setId(123l);

        QrCodeDetail activeDetail = new QrCodeDetail();
        activeDetail.setStatus(QrCodeDetailStatus.ACTIVE);

        // Tworzenie mocka repozytorium (repo)
        QrCodeDetailRepo repo = mock(QrCodeDetailRepo.class);
        when(repo.findByQrCodeAndStatus(qrCode, QrCodeDetailStatus.ACTIVE)).thenReturn(List.of(activeDetail));

        // Tworzenie instancji klasy testowanej
        QrCodeDetailServiceImpl qrCodeDetailService = new QrCodeDetailServiceImpl(repo);

        // Wywołanie metody testowanej
        QrCodeDetail result = qrCodeDetailService.getActiveByQrCode(qrCode);

        // Sprawdzenie, czy zwrócono poprawny detal
        assertEquals(activeDetail, result);
    }

    @Test
    public void testGetActiveByQrCode_NotFound() {
        // Przygotowanie danych testowych
        QrCode qrCode = new QrCode();
        qrCode.setId(123l);

        // Tworzenie mocka repozytorium (repo) z brakiem aktywnego detalu
        QrCodeDetailRepo repo = mock(QrCodeDetailRepo.class);
        when(repo.findByQrCodeAndStatus(qrCode, QrCodeDetailStatus.ACTIVE)).thenReturn(List.of());

        // Tworzenie instancji klasy testowanej
        QrCodeDetailServiceImpl qrCodeDetailService = new QrCodeDetailServiceImpl(repo);

        // Sprawdzenie, czy wywołanie zwraca oczekiwany wyjątek
        BaseException exception = assertThrows(BaseException.class, () -> qrCodeDetailService.getActiveByQrCode(qrCode));

        // Sprawdzenie, czy treść wyjątku jest poprawna
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());

    }


}
