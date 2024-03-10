package me.gijung.DMforU.service;

import com.google.firebase.messaging.*;
import lombok.RequiredArgsConstructor;
import me.gijung.DMforU.model.dto.TokensDto;
import me.gijung.DMforU.service.firebase.GoogleToken;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class GoogleService {
    private final GoogleToken googleToken;

    public void updateToken(TokensDto tokensDto){
        googleToken.update_Token(tokensDto);
    }

    public void deleteToken(TokensDto tokensDto){
        googleToken.delete_Token(tokensDto);
    }

    @Deprecated
    public void AllDeleteTopic(TokensDto tokensDto) throws FirebaseMessagingException {
        googleToken.AllDeleteTopic(tokensDto);
    }
}
