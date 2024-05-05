package com.dmforu.subscribe.service;

import com.dmforu.subscribe.dto.DepartmentDto;
import com.dmforu.subscribe.dto.InitRequestDto;
import com.dmforu.subscribe.dto.NoticeMapper;
import com.dmforu.subscribe.dto.TokensDto;
import com.dmforu.subscribe.token.Token;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InitService {

    private final Token<TokensDto> googleToken;
    private final Token<TokensDto> redisToken;
    private final Department department;

    public void createTokenDepartment(InitRequestDto requestInitDto) {
        department.createDepartment(mapToInitRequestDto(requestInitDto));
        redisToken.createToken(mapToInitRequestDto1(requestInitDto));
        googleToken.createToken(mapToInitRequestDto1(requestInitDto));
    }

    private TokensDto mapToInitRequestDto1(InitRequestDto requestInitDto) {
        return NoticeMapper.RequestTokenDtoToServiceTokensDto(requestInitDto);
    }
    private DepartmentDto mapToInitRequestDto(InitRequestDto requestInitDto){
        return NoticeMapper.RequestInitDtoToServiceDepartmentDto(requestInitDto);
    }
}