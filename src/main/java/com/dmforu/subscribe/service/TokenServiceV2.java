package com.dmforu.subscribe.service;

import com.dmforu.subscribe.dtoV2.KeywordDTO;
import com.dmforu.subscribe.dtoV2.KeywordStatusDTO;
import com.dmforu.subscribe.dtoV2.InitTokensDTO;
import com.dmforu.subscribe.entity.Token;
import com.dmforu.subscribe.repository.TokenRepository;
import com.dmforu.subscribe.util.mapToEnitty;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TokenServiceV2 {

    private final TokenRepository tokenRepository;
    public void createInitTokenInformation(InitTokensDTO initTokensDto) {
        tokenRepository.save(mapToEnitty.tokenDtoMapToEntity(initTokensDto));
    }

    @Transactional
    public void updateKeyword(KeywordDTO keywordDto) {

        Optional<Token> byId = tokenRepository.findById(keywordDto.getToken());
        if (byId.isPresent()) {
            Token token = byId.get();
            token.updateKeywords(keywordDto.getKeywordsList());
        }
    }

    @Transactional
    public void updateKeywordStatus(KeywordStatusDTO keywordStatusDTO) {
        Optional<Token> byId = tokenRepository.findById(keywordStatusDTO.getToken());
        if (byId.isPresent()) {
            Token token = byId.get();
            token.updateKeywordStatus(keywordStatusDTO.isKeywordOnOFF());
        }

    }

}
