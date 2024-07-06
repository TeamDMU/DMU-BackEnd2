package com.dmforu.v2_subscrible.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.auth.oauth2.ServiceAccountCredentials;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;

@Component
@Slf4j
public class RefreshToken {

    // Servcer AccessToken 갱신
    @Scheduled(fixedRate = 5000000)
    private void refreshAccessToken() throws IOException {
        Resource resource = new ClassPathResource("key/fire-base-key.json");

        InputStream inputStream = resource.getInputStream();
        GoogleCredentials credentials = ServiceAccountCredentials
                .fromStream(inputStream)
                .createScoped(Collections.singletonList("https://www.googleapis.com/auth/firebase.messaging"));
        credentials.refreshIfExpired();


//        log.info("Refresh_Token_log : " + LocalDateTime.now());

    }
}
