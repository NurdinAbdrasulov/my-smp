package it.camerino.qrcodegenerator.service.impl;

import it.camerino.qrcodegenerator.dto.QrCodeDto;
import it.camerino.qrcodegenerator.entity.QrCode;
import it.camerino.qrcodegenerator.entity.User;
import it.camerino.qrcodegenerator.repository.QrCodeRepo;
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

@ExtendWith(MockitoExtension.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class QrCodeServiceImplTest {

    QrCodeServiceImpl service;
//    @Mock
    QrCodeRepo repo;

    QrCodeServiceImplTest() {
        Mockito.mock(repo).

        service = new QrCodeServiceImpl(repo);
    }
    @Test
    public void CreateShouldNotReturnZero()
    {
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

        doReturn(null).when(repo).save(any());

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