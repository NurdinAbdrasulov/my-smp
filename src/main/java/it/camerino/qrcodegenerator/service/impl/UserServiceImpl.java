package it.camerino.qrcodegenerator.service.impl;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import it.camerino.qrcodegenerator.entity.User;
import it.camerino.qrcodegenerator.exception.BaseException;
import it.camerino.qrcodegenerator.repository.UserRepo;
import it.camerino.qrcodegenerator.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserServiceImpl implements UserService {

    UserRepo repo;

    @Override
    public String registerUser(String email, String password) {
        try {
            UserRecord.CreateRequest request = new UserRecord.CreateRequest()
                    .setEmail(email)
                    .setPassword(password);

            UserRecord userRecord = FirebaseAuth.getInstance().createUser(request);
            saveUserDataInDB(email, password);

            return userRecord.getUid();
        } catch (Exception e) {
            throw new BaseException("Error with user registration", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public UserRecord getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserRecord userRecord = null;
        try {
            userRecord = FirebaseAuth.getInstance().getUser(authentication.getName());
        } catch (FirebaseAuthException e) {
            throw new BaseException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return userRecord;
    }

    @Override
    public User getCurrentUserEntity() {
        UserRecord currentUser = getCurrentUser();

        return repo.findByUsername(
                currentUser.getEmail()).orElseGet(() -> saveUserDataInDB(currentUser.getEmail(), null)
        );
    }


    private User saveUserDataInDB(String email, String password) {
        return repo.save(
                User.builder()
                        .username(email)
                        .password(password)
                        .createdAt(LocalDateTime.now())
                        .build()
        );
    }

}
