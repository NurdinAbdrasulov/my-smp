package it.camerino.qrcodegenerator.service;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    String registerUser(String email, String password);
}
