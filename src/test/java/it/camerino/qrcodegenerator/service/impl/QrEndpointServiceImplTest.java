package it.camerino.qrcodegenerator.service.impl;

import it.camerino.qrcodegenerator.entity.QrCode;
import it.camerino.qrcodegenerator.enums.QrCodeDetailStatus;
import it.camerino.qrcodegenerator.repository.QrCodeDetailRepo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class QrEndpointServiceImplTest {


    @Test
    public void testGetLinkByQr_NotFound() {
        // Przygotowanie danych testowych
        QrCode qrCode = new QrCode();
        qrCode.setId(123l);

        // Tworzenie mocka repozytorium (repo) z brakiem aktywnego detalu
        QrCodeDetailRepo repo = mock(QrCodeDetailRepo.class);
        when(repo.findByQrCodeAndStatus(qrCode, QrCodeDetailStatus.ACTIVE)).thenReturn(null);

        // Tworzenie instancji klasy testowanej
        QrCodeDetailServiceImpl qrCodeDetailService = new QrCodeDetailServiceImpl(repo);

        // Sprawdzenie, czy wywołanie zwraca oczekiwany wyjątek
        NullPointerException exception = assertThrows(NullPointerException.class, () -> qrCodeDetailService.getLinkByQr(qrCode));

    }
}

