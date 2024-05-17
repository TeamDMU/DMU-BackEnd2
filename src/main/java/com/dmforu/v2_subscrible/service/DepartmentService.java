package com.dmforu.v2_subscrible.service;

import com.dmforu.v2_subscrible.model.dto.DepartmentStatusDTO;
import com.dmforu.v2_subscrible.model.entity.Token;
import com.dmforu.v2_subscrible.repository.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final TokenRepository tokenRepository;

    @Transactional
    public void updateDepartmentStatus(DepartmentStatusDTO departmentStatusDTO) {

        Optional<Token> byId = tokenRepository.findById(departmentStatusDTO.getToken());
        Token token = byId.get();
        token.updateDepartmentStatus(departmentStatusDTO.getDepartmentOnOFF());

    }
}