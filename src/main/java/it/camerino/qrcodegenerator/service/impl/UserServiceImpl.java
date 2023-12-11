package it.camerino.qrcodegenerator.service.impl;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
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
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

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

    private void checkCredentioal(String email, String password)  {

        try {
            UserRecord userByEmail = FirebaseAuth.getInstance().getUserByEmail(email);


        } catch (FirebaseAuthException e) {
            throw new BaseException("FireBase user not found", HttpStatus.BAD_REQUEST);
        }
    }

    public String signIn(String idToken) {
        try {
            // Verify the provided email and password
            FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(idToken);

            // If the verification is successful, respond with the ID token
            return "User successfully authenticated with UID: " + decodedToken.getUid();
        } catch (FirebaseAuthException e) {
            // Handle authentication failure
            return "Authentication failed: " + e.getMessage();
        }
    }


    private void saveUserDataInDB(String email, String password){
        repo.save(
                User.builder()
                        .username(email)//todo rename
                        .password(password)
                        .build()
        );
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String farebaseToken = username;

        FirebaseToken decodedToken = null;
        try {
            decodedToken = FirebaseAuth.getInstance().verifyIdToken(farebaseToken);

//            FirebaseAuth.getInstance().getUserByProviderUid()

            return new MyUserDetails(User.builder()
                    .username(username)//todo
                    .build()
            );
        } catch (FirebaseAuthException e) {
            throw new BaseException("Auth custom exception", HttpStatus.BAD_REQUEST);
        }

    }
}
