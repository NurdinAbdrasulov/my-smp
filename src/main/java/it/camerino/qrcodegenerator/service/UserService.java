package it.camerino.qrcodegenerator.service;

import com.google.firebase.auth.UserRecord;

public interface UserService {
    String registerUser(String email, String password);

    UserRecord getCurrentUser();
}
