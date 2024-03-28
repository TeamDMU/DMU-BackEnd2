package me.gijung.DMforU.service;

import lombok.RequiredArgsConstructor;
import me.gijung.DMforU.model.dto.RequestInitDto;
import me.gijung.DMforU.model.dto.ServiceDepartmentDto;
import me.gijung.DMforU.model.dto.ServiceTokensDto;
import me.gijung.DMforU.service.redis.Department;
import me.gijung.DMforU.service.token.Token;
import org.springframework.stereotype.Service;

import static me.gijung.DMforU.utils.NoticeMapper.RequestInitDtoToServiceDepartmentDto;
import static me.gijung.DMforU.utils.NoticeMapper.RequestTokenDtoToServiceTokensDto;

@Service
@RequiredArgsConstructor
public class InitService {

    private final Token<ServiceTokensDto> googleToken;
    private final Token<ServiceTokensDto> redisToken;
    private final DepartmentService departmentService;

    public void createTokenDepartment(RequestInitDto requestInitDto) {
        departmentService.createDepartment(mapToRequestInitDto2(requestInitDto));
        redisToken.createToken(mapToRequestInitDto1(requestInitDto));
        googleToken.createToken(mapToRequestInitDto1(requestInitDto));
    }

    private ServiceTokensDto mapToRequestInitDto1(RequestInitDto requestInitDto) {
        return RequestTokenDtoToServiceTokensDto(requestInitDto);
    }
    private ServiceDepartmentDto mapToRequestInitDto2(RequestInitDto requestInitDto){
        return RequestInitDtoToServiceDepartmentDto(requestInitDto);
    }
}
