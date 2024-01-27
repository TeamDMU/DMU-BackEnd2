package me.gijung.DMforU.service;

import com.google.firebase.messaging.FirebaseMessagingException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TokenService<T> {
        void updateToken(String topic, List<String> Tokens) throws FirebaseMessagingException;
        void deleteToken(String topic, List<String> Tokens) throws FirebaseMessagingException;
}
