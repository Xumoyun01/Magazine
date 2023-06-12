package com.example.simpelproject.service;

import com.example.simpelproject.dto.ErrorDto;
import com.example.simpelproject.dto.ForeignDebtDto;
import com.example.simpelproject.dto.ResponseDto;
import com.example.simpelproject.model.ForeignDebt;
import com.example.simpelproject.repository.ForeignDebtRepository;
import com.example.simpelproject.service.mapper.ForeignDebtMapper;
import com.example.simpelproject.service.validation.ForeignDebtValidate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ForeignDebtService {

    private final ForeignDebtMapper foreignDebtMapper;
    private final ForeignDebtRepository foreignDebtRepository;
    private final ForeignDebtValidate foreignDebtValidate;

    public ResponseDto<ForeignDebtDto> createForeignDebt(ForeignDebtDto dto) {

        try {
            ForeignDebt foreignDebt = foreignDebtMapper.toEntity(dto);
            foreignDebt.setCreatedAt(LocalDateTime.now());
            foreignDebtRepository.save(foreignDebt);
            return ResponseDto.<ForeignDebtDto>builder()
                    .message(String.format("This is foreignDebt %d id successful created!", foreignDebt.getForeignId()))
                    .success(true)
                    .data(foreignDebtMapper.toDto(foreignDebt))
                    .build();
        } catch (Exception c) {
            return ResponseDto.<ForeignDebtDto>builder()
                    .code(-3)
                    .message("ForeignDebt while saving error: " + c.getMessage())
                    .build();

        }
    }

    public ResponseDto<ForeignDebtDto> getForeignDebt(Integer foreignDebtId) {
        try {
            Optional<ForeignDebt> optional = foreignDebtRepository.findByForeignIdAndDeletedAtIsNull(foreignDebtId);
            if (optional.isEmpty()) {
                return ResponseDto.<ForeignDebtDto>builder()
                        .message("ForeignDebt is not found!")
                        .code(-1)
                        .build();
            }
            return ResponseDto.<ForeignDebtDto>builder()
                    .message("OK")
                    .success(true)
                    .data(foreignDebtMapper.toDto(optional.get()))
                    .build();
        } catch (Exception c) {
            return ResponseDto.<ForeignDebtDto>builder()
                    .code(-3)
                    .message("Database Error: " + c.getMessage())
                    .data(null)
                    .build();
        }
    }

    public ResponseDto<ForeignDebtDto> updateForeignDebt(ForeignDebtDto dto, Integer foreignDebtId) {

        Optional<ForeignDebt> optional = foreignDebtRepository.findByForeignIdAndDeletedAtIsNull(foreignDebtId);
        if (optional.isEmpty()) {
            return ResponseDto.<ForeignDebtDto>builder()
                    .message("ForeignDebt is not found!")
                    .code(-1)
                    .build();
        }

        try {
            ForeignDebt foreignDebt = foreignDebtMapper.toEntity(dto);
            foreignDebt.setForeignId(optional.get().getForeignId());
            foreignDebt.setCreatedAt(optional.get().getCreatedAt());
            foreignDebt.setDeletedAt(optional.get().getDeletedAt());
            foreignDebt.setUpdatedAt(LocalDateTime.now());
            foreignDebtRepository.save(foreignDebt);
            return ResponseDto.<ForeignDebtDto>builder()
                    .message(String.format("This is foreignDebt %d id successful updated!", foreignDebt.getForeignId()))
                    .success(true)
                    .data(foreignDebtMapper.toDto(foreignDebt))
                    .build();
        } catch (Exception e) {
            return ResponseDto.<ForeignDebtDto>builder()
                    .message("ForeignDebt while saving error :: {}" + e.getMessage())
                    .code(-3)
                    .build();
        }
    }

    public ResponseDto<ForeignDebtDto> deleteForeignDebt(Integer foreignDebtId) {
        Optional<ForeignDebt> optional = foreignDebtRepository.findByForeignIdAndDeletedAtIsNull(foreignDebtId);
        if (optional.isEmpty()) {
            return ResponseDto.<ForeignDebtDto>builder()
                    .message("ForeignDebt is not found!")
                    .code(-1)
                    .build();
        }
        try {
            ForeignDebt foreignDebt = optional.get();
            foreignDebt.setDeletedAt(LocalDateTime.now());
            foreignDebtRepository.save(foreignDebt);
            return ResponseDto.<ForeignDebtDto>builder()
                    .success(true)
                    .message(String.format("This is foreignDebt %d id successful deleted!", foreignDebtId))
                    .data(foreignDebtMapper.toDto(foreignDebt))
                    .build();
        } catch (Exception e) {
            return ResponseDto.<ForeignDebtDto>builder()
                    .message("ForeignDebt while saving error :: {}" + e.getMessage())
                    .code(-3)
                    .build();
        }
    }

    public ResponseDto<Page<ForeignDebtDto>> getAdventSearch(Map<String, String> params) {
        return null;
    }
}
