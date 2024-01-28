package me.gijung.DMforU.service;

import com.google.firebase.messaging.FirebaseMessagingException;
import me.gijung.DMforU.model.dto.TokensDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TokenService<T> {
        void updateToken(TokensDto tokensDto) throws FirebaseMessagingException;
        void deleteToken(TokensDto tokensDto) throws FirebaseMessagingException;
}
