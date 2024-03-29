package it.camerino.qrcodegenerator.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class FirebaseConfig {

    private static final String SERVICE_ACCOUNT_KEY_PATH = "key/spm-qr-firebase-adminsdk-qzgds-d37bd76c77.json";


    @Bean
    public FirebaseApp initializeFirebase() throws IOException {
        var targetStream = this.getClass().getClassLoader().getResourceAsStream(SERVICE_ACCOUNT_KEY_PATH);

        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(targetStream))
                .setDatabaseUrl("https://spm-qr.firebaseio.com")
                .build();

        return FirebaseApp.initializeApp(options);
    }
}
