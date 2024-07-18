package com.dmforu.subscribe.service;

import com.dmforu.subscribe.dtoV2.*;
import com.dmforu.subscribe.entity.Token;
import com.dmforu.subscribe.repository.TokenRepository;
import com.dmforu.subscribe.util.mapToEnitty;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class KeywordService {

    private final TokenRepository tokenRepository;
//    private final RedisToken redisToken;

    public void refreshToken(String token) {
//        redisToken.refreshToken(token);
    }


    public void createInitTokenInformation(InitTokensDTO initTokensDto) {
        tokenRepository.save(mapToEnitty.tokenDtoMapToEntity(initTokensDto));
    }

    @Transactional
    public void updateKeyword(KeywordDTO keywordDto) {
        Optional<Token> byId = tokenRepository.findById(keywordDto.getToken());
        Token token = returnToken(byId);
        if (Objects.isNull(token)) {
            if (keywordDto.getKeywordsList() == null || keywordDto.getKeywordsList().isEmpty()) {
                newToken(keywordDto.getToken(), null);
            } else {
                newToken(keywordDto.getToken(), keywordDto.getKeywordsList());
            }

            return;
        }

        if (keywordDto.getKeywordsList() == null || keywordDto.getKeywordsList().isEmpty()) {
            token.updateKeywords(null);
            return;
        }

        token.updateKeywords(keywordDto.getKeywordsList());
    }

    @Transactional
    public void updateKeywordStatus(KeywordStatusDTO keywordStatusDTO) {
        Optional<Token> byId = tokenRepository.findById(keywordStatusDTO.getToken());
        Token token = returnToken(byId);
        if (Objects.isNull(token)) {
            newToken(keywordStatusDTO.getToken(), keywordStatusDTO.getKeywordsList(), keywordStatusDTO.isKeywordOnOFF());
            return;
        }

        if (token.getKeywordsList() == null && keywordStatusDTO.getKeywordsList() != null && !keywordStatusDTO.getKeywordsList().isEmpty()) {
            token.updateKeywords(keywordStatusDTO.getKeywordsList());
            token.updateKeywordStatus(keywordStatusDTO);
            return;
        }

        token.updateKeywordStatus(keywordStatusDTO);
    }

    private Token returnToken(Optional<Token> token) {
        return token.orElse(null);
    }

    private void newToken(String token, List<String> keywordList) {
        tokenRepository.save(
                new Token(token, null, keywordList, false, true)
        );
    }

    private void newToken(String token, List<String> keywordList, boolean keywordOnOff) {
        tokenRepository.save(
                new Token(token, null, keywordList, false, keywordOnOff)
        );
    }
}
