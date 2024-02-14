package it.camerino.qrcodegenerator.service.impl;
import it.camerino.qrcodegenerator.dto.QrCodeDto;
import it.camerino.qrcodegenerator.entity.QrCode;
import it.camerino.qrcodegenerator.entity.User;
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


public class UserServiceImplTest {

    @Test
    public void testSaveUserDataInDB() {
        String email = "john@gmail.com";
        String password = "john123";

        UserRepo repo = mock(UserRepo.class);
        UserServiceImpl userService = new UserServiceImpl(repo);

        User savedUser = userService.saveUserDataInDB(email, password);

        verify(repo).save(argThat(user ->
                user.getUsername().equals(email) &&
                        user.getPassword().equals(password)
        ));

    }
}
