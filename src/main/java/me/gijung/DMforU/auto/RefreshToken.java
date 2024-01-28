package me.gijung.DMforU.auto;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.auth.oauth2.ServiceAccountCredentials;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;

@Component
public class RefreshToken {

    private static final String SERVICE_ACCOUNT_KEY_FILE = "src/main/resources/key/fire-base-key.json";

    @Scheduled(fixedRate = 5000000) // Scheduled every hour
    private void refreshAccessToken() throws IOException {
        GoogleCredentials credentials = ServiceAccountCredentials
                .fromStream(new FileInputStream(SERVICE_ACCOUNT_KEY_FILE))
                .createScoped(Collections.singletonList("https://www.googleapis.com/auth/firebase.messaging"));
        credentials.refreshIfExpired();
        String tokenValue = credentials.getAccessToken().getTokenValue();
        System.out.println("Refresh Token ::: " + tokenValue + "| Date ::: " +
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }
}
