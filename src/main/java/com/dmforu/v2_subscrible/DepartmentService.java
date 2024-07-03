package com.dmforu.v2_subscrible.department;

import com.dmforu.v2_subscrible.model.dto.DepartmentDTO;
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
        token.updateDepartmentStatus(departmentStatusDTO.isDepartmentOnOFF());
    }

    @Transactional
    public void updateDepartment(DepartmentDTO departmentDTO) {
        Optional<Token> byId = tokenRepository.findById(departmentDTO.getToken());
        Token token = byId.get();
        token.updateDepartment(departmentDTO.getDepartment());
    }
}