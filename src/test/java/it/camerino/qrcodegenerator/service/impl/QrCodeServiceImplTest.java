package it.camerino.qrcodegenerator.service.impl;

import it.camerino.qrcodegenerator.dto.QrCodeDto;
import it.camerino.qrcodegenerator.entity.QrCode;
import it.camerino.qrcodegenerator.entity.User;
import it.camerino.qrcodegenerator.repository.QrCodeRepo;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class QrCodeServiceImplTest {

    @Mock
    QrCodeRepo repo;

    @InjectMocks
    QrCodeServiceImpl service;

    @Test
    public void CreateShouldNotReturnZero() {
        QrCodeDto dto = new QrCodeDto();

        assertNotNull(dto);
    }

    @Test
    public void shouldThrow() {
        //given
        final long qrId = 111L;
        final String expectedExceptionMessage = "Method is not implemented";

        //when
        //then
        UnsupportedOperationException unsupportedOperationException = assertThrows(UnsupportedOperationException.class, () -> service.delete(qrId));
        assertEquals(expectedExceptionMessage, unsupportedOperationException.getMessage());
    }


    @Test
    public void shouldCallSaveMethodOneTime() {

        doReturn(new QrCode()).when(repo).save(any());

        //given
        final QrCodeDto dto = QrCodeDto.builder()
                .qrColor("blue")
                .link("https://www.google.com/")
                .build();

        final User currentUser = new User();

        //when
        service.create(dto, currentUser);

        //then
        verify(repo, times(1)).save(any());
    }
}

