package it.camerino.qrcodegenerator.config.security;

import lombok.Getter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Getter
public class FirebaseAuthenticationToken extends UsernamePasswordAuthenticationToken {

    private final String idToken;

    public FirebaseAuthenticationToken(String idToken) {
        super(null, null);
        this.idToken = idToken;
    }

}

