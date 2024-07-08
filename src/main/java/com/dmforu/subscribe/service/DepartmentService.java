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
            System.out.println("Null Update Department Status!");
            tokenRepository.save(
                    new Token(departmentStatusDTO.getToken(), departmentStatusDTO.getDepartment(), null, true, false)
            );
        }
        token.updateDepartmentStatus(departmentStatusDTO.isDepartmentOnOFF());
    }

    @Transactional
    public void updateDepartment(DepartmentDTO departmentDTO) {
        Optional<Token> byId = tokenRepository.findById(departmentDTO.getToken());
        Token token = returnToken(byId);
        if (Objects.isNull(token)) {
            System.out.println("Null Update Department!");
            tokenRepository.save(
                    new Token(departmentDTO.getToken(), departmentDTO.getDepartment(), null, true, false)
            );
        }
        token.updateDepartment(departmentDTO.getDepartment());
    }

    private Token returnToken(Optional<Token> token) {
        return token.orElse(null);
    }
}