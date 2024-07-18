package com.dmforu.subscribe.service;

import com.dmforu.subscribe.dtoV2.DepartmentDTO;
import com.dmforu.subscribe.dtoV2.DepartmentStatusDTO;
import com.dmforu.subscribe.entity.Token;
import com.dmforu.subscribe.repository.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final TokenRepository tokenRepository;

    @Transactional
    public void updateDepartment(DepartmentDTO departmentDTO) {
        Optional<Token> byId = tokenRepository.findById(departmentDTO.getToken());
        Token token = returnToken(byId);

        if (Objects.isNull(token)) {
            newToken(departmentDTO.getToken(), departmentDTO.getDepartment());
            return;
        }

        token.updateDepartment(departmentDTO.getDepartment());
    }

    @Transactional
    public void updateDepartmentStatus(DepartmentStatusDTO departmentStatusDTO) {
        Optional<Token> byId = tokenRepository.findById(departmentStatusDTO.getToken());
        Token token = returnToken(byId);

        // 토큰이 없는 경우
        if (Objects.isNull(token)) {
            newToken(departmentStatusDTO.getToken(), departmentStatusDTO.getDepartment(), departmentStatusDTO.isDepartmentOnOFF());
            return;
        }

        // 토큰안에 학과 정보가 없는 경우
        if (token.getDepartment() == null) {
            token.updateDepartment(departmentStatusDTO.getDepartment());
            token.updateDepartmentStatus(departmentStatusDTO.isDepartmentOnOFF());
            return;
        }

        // 정상적인 토큰이며, 상태 변경만 하면 되는 경우
        token.updateDepartmentStatus(departmentStatusDTO.isDepartmentOnOFF());

    }

    private Token returnToken(Optional<Token> token) {
        return token.orElse(null);
    }

    private void newToken(String token, String department) {
        tokenRepository.save(
                new Token(token, department, null, true, false)
        );
    }

    private void newToken(String token, String department, boolean departmentOnOff) {
        tokenRepository.save(
                new Token(token, department, null, departmentOnOff, false)
        );
    }
}