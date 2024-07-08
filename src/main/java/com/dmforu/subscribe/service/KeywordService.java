package com.dmforu.subscribe.service;

import com.dmforu.subscribe.dtoV2.*;
import com.dmforu.subscribe.entity.Token;
import com.dmforu.subscribe.repository.TokenRepository;
import com.dmforu.subscribe.util.mapToEnitty;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
            System.out.println("Null Update Keyword!");
            tokenRepository.save(
                    new Token(keywordDto.getToken(), null, keywordDto.getKeywordsList(), false, true)
            );
        }
        token.updateKeywords(keywordDto.getKeywordsList());
    }

    @Transactional
    public void updateKeywordStatus(KeywordStatusDTO keywordStatusDTO) {
        Optional<Token> byId = tokenRepository.findById(keywordStatusDTO.getToken());
        Token token = returnToken(byId);
        if (Objects.isNull(token)) {
            System.out.println("Null Update KeywordStatus!");
            tokenRepository.save(
                    new Token(keywordStatusDTO.getToken(), null, keywordStatusDTO.getKeywordsList(), false, true)
            );
        }
        token.updateKeywordStatus(keywordStatusDTO);
    }

    private Token returnToken(Optional<Token> token) {
        return token.orElse(null);
    }
}
