package me.gijung.DMforU.service.token;

import com.google.firebase.messaging.*;
import lombok.RequiredArgsConstructor;
import me.gijung.DMforU.model.dto.TokensDto;
import me.gijung.DMforU.service.firebase.GoogleToken;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class GoogleService{
    private final GoogleToken googleToken;

    public void update_Token(TokensDto tokensDto) {
        googleToken.update_Token(tokensDto);
    }

    public void delete_Token(TokensDto tokensDto) {
        googleToken.delete_Token(tokensDto);
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
