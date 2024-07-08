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
    public void updateDepartmentStatus(DepartmentStatusDTO departmentStatusDTO) {
        Optional<Token> byId = tokenRepository.findById(departmentStatusDTO.getToken());
        Token token = returnToken(byId);
        if (Objects.isNull(token)) {
            newToken(departmentStatusDTO.getToken(), departmentStatusDTO.getDepartment());
        } else {
            token.updateDepartmentStatus(departmentStatusDTO.isDepartmentOnOFF());
        }
    }

    @Transactional
    public void updateDepartment(DepartmentDTO departmentDTO) {
        Optional<Token> byId = tokenRepository.findById(departmentDTO.getToken());
        Token token = returnToken(byId);
        if (Objects.isNull(token)) {
            newToken(departmentDTO.getToken(), departmentDTO.getDepartment());
        } else {
            token.updateDepartment(departmentDTO.getDepartment());
        }
    }

    private Token returnToken(Optional<Token> token) {
        return token.orElse(null);
    }

    private void newToken(String token, String department) {
        tokenRepository.save(
                new Token(token, department, null, true, false)
        );
    }
}