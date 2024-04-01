package me.gijung.DMforU.service;

import com.google.firebase.messaging.FirebaseMessagingException;
import lombok.RequiredArgsConstructor;
import me.gijung.DMforU.model.dto.RequestTokenDto;

import me.gijung.DMforU.model.dto.TokensDto;
import me.gijung.DMforU.service.token.Token;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import java.util.concurrent.ExecutionException;

import static me.gijung.DMforU.utils.NoticeMapper.RequestTokenDtoToServiceTokensDto;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final Token<TokensDto> googleToken;
    private final Token<TokensDto> redisToken;

    //토큰 생명주기 연장
    public void refreshToken(RequestTokenDto tokensDto) {
        redisToken.refreshToken(mapToRequestTokenDto(tokensDto));
    }


    //토픽 업데이트
//    @Async("customThreadPool")
    public void updateTopic(RequestTokenDto tokensDto) throws FirebaseMessagingException, ExecutionException, InterruptedException {
        redisToken.updateToken(mapToRequestTokenDto(tokensDto));
        googleToken.updateToken(mapToRequestTokenDto(tokensDto));
    }

    public void deleteTopic(RequestTokenDto tokensDto) {
        redisToken.deleteToken(mapToRequestTokenDto(tokensDto));
        googleToken.deleteToken(mapToRequestTokenDto(tokensDto));
    }

    private TokensDto mapToRequestTokenDto(RequestTokenDto requestTokenDto) {
        return RequestTokenDtoToServiceTokensDto(requestTokenDto);
    }
}
