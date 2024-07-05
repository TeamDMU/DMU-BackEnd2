package com.dmforu.v2_subscribe;

import com.dmforu.v2_subscribe.model.dto.DepartmentDTO;
import com.dmforu.v2_subscribe.model.dto.DepartmentStatusDTO;
import com.dmforu.v2_subscribe.model.dto.KeywordDTO;
import com.dmforu.v2_subscribe.model.dto.KeywordStatusDTO;
import com.dmforu.v2_subscribe.model.dto.InitTokensDTO;
import com.dmforu.v2_subscribe.model.entity.Token;
import com.dmforu.v2_subscribe.repository.TokenRepository;
import com.dmforu.v2_subscribe.util.mapToEnitty;
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

    @Transactional
    public void updateDepartmentStatus(DepartmentStatusDTO departmentStatusDTO) {
        Optional<Token> byId = tokenRepository.findById(departmentStatusDTO.getToken());
        Token token = byId.get();
        token.updateDepartmentStatus(departmentStatusDTO.isDepartmentOnOFF());
    }

    @Transactional
    public void updateDepartment(DepartmentDTO departmentDTO) {
        Optional<Token> byId = tokenRepository.findById(departmentDTO.getToken());
        Token token = byId.get();
        token.updateDepartment(departmentDTO.getDepartment());
    }

}
