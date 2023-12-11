package it.camerino.qrcodegenerator.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.IOException;

@Configuration
public class FirebaseConfig {

    private static final String SERVICE_ACCOUNT_KEY = "key/spm-qr-firebase-adminsdk-qzgds-d37bd76c77.json";


    @Bean
    public FirebaseApp initializeFirebase() throws IOException {

//        ClassLoader classLoader = getClass().getClassLoader();
//        targetStream = classLoader.getResourceAsStream(SERVICE_ACCOUNT_KEY);

        FileInputStream serviceAccount = new FileInputStream("src/main/resources/key/spm-qr-firebase-adminsdk-qzgds-d37bd76c77.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://spm-qr.firebaseio.com")
                .build();

        return FirebaseApp.initializeApp(options);
    }
}
