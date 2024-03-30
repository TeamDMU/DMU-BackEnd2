package me.gijung.DMforU.service;

import lombok.RequiredArgsConstructor;
import me.gijung.DMforU.model.dto.DepartmentDto;
import me.gijung.DMforU.model.dto.InitRequestDto;
import me.gijung.DMforU.model.dto.TokensDto;
import me.gijung.DMforU.service.redis.Department;
import me.gijung.DMforU.service.token.Token;
import org.springframework.stereotype.Service;

import static me.gijung.DMforU.utils.NoticeMapper.RequestInitDtoToServiceDepartmentDto;
import static me.gijung.DMforU.utils.NoticeMapper.RequestTokenDtoToServiceTokensDto;

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
        return RequestTokenDtoToServiceTokensDto(requestInitDto);
    }
    private DepartmentDto mapToInitRequestDto(InitRequestDto requestInitDto){
        return RequestInitDtoToServiceDepartmentDto(requestInitDto);
    }
}
