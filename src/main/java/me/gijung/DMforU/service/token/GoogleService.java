package me.gijung.DMforU.service.token;

import com.google.firebase.messaging.*;
import lombok.RequiredArgsConstructor;
import me.gijung.DMforU.model.dto.TokensDto;
import me.gijung.DMforU.service.firebase.GoogleToken;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class GoogleService implements TokenService<TokensDto> {
    private final GoogleToken googleToken;

    public void updateToken(TokensDto tokensDto) {
        try {
            googleToken.updateToken(tokensDto);
        } catch (FirebaseMessagingException exception) {
            throw new IllegalArgumentException("FirebaseMessaging 예외 발생");
        }
    }

    public void deleteToken(TokensDto tokensDto) {
        try {
            googleToken.deleteToken(tokensDto);
        } catch (FirebaseMessagingException exception) {
            throw new IllegalArgumentException("FirebaseMessaging 예외 발생");
        }
    }

    /**
     * redisConfig가 삭제됨에 따라 추후 업데이트가 있지 않는 한 사용하지 않음
     *
     * @param tokensDto
     * @throws FirebaseMessagingException
     */
    @Deprecated
    public void allDeleteTopic(TokensDto tokensDto) throws FirebaseMessagingException {
        googleToken.AllDeleteTopic(tokensDto);
    }
}
