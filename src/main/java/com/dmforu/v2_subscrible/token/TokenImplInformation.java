package com.dmforu.v2_subscrible.token;

import com.dmforu.v2_subscrible.model.dto.KeywordDTO;
import com.dmforu.v2_subscrible.model.dto.KeywordStatusDTO;
import com.dmforu.v2_subscrible.model.dto.InitTokensDTO;
import com.dmforu.v2_subscrible.model.entity.Token;
import com.dmforu.v2_subscrible.repository.TokenRepository;
import com.dmforu.v2_subscrible.util.mapToEnitty;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TokenImplInformation{

    private final TokenRepository tokenRepository;
    public void createInitTokenInformation(InitTokensDTO initTokensDto) {
        tokenRepository.save(mapToEnitty.tokenDtoMapToEntity(initTokensDto));
    }

    @Transactional
    public void updateKeyword(KeywordDTO keywordDto) {

        Optional<Token> byId = tokenRepository.findById(keywordDto.getToken());
        Token token = byId.get();
        token.updateKeywords(keywordDto.getKeywordsList());

    }

    @Transactional
    public void updateKeywordStatus(KeywordStatusDTO keywordStatusDTO) {
        Optional<Token> byId = tokenRepository.findById(keywordStatusDTO.getToken());
        Token token = byId.get();
        token.updateKeywordStatus(keywordStatusDTO.getKeywordOnOFF());

    }

}
