package com.example.simpelproject.service;

import com.example.simpelproject.dto.ErrorDto;
import com.example.simpelproject.dto.LoanerDto;
import com.example.simpelproject.dto.ResponseDto;
import com.example.simpelproject.model.Loaner;
import com.example.simpelproject.repository.LoanerRepository;
import com.example.simpelproject.service.mapper.LoanerMapper;
import com.example.simpelproject.service.validation.LoanerValidate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoanerService {

    private final LoanerMapper loanerMapper;
    private final LoanerRepository loanerRepository;
    private final LoanerValidate loanerValidate;

    public ResponseDto<LoanerDto> createLoaner(LoanerDto dto) {
        try {
            Loaner loaner = loanerMapper.toEntity(dto);
            loaner.setCreatedAt(LocalDateTime.now());
            loanerRepository.save(loaner);
            return ResponseDto.<LoanerDto>builder()
                    .message(String.format("This is loaner %d id successful created!", loaner.getLoanerId()))
                    .success(true)
                    .data(loanerMapper.toDto(loaner))
                    .build();
        } catch (Exception c) {
            return ResponseDto.<LoanerDto>builder()
                    .code(-3)
                    .message("Loaner while saving error: " + c.getMessage())
                    .build();

        }
    }

    public ResponseDto<LoanerDto> getLoaner(Integer loanerId) {
        try {
            Optional<Loaner> optional = loanerRepository.findByLoanerIdAndDeletedAtIsNull(loanerId);
            if (optional.isEmpty()) {
                return ResponseDto.<LoanerDto>builder()
                        .message("Loaner is not found!")
                        .code(-1)
                        .build();
            }
            return ResponseDto.<LoanerDto>builder()
                    .message("OK")
                    .success(true)
                    .data(loanerMapper.toDto(optional.get()))
                    .build();
        } catch (Exception c) {
            return ResponseDto.<LoanerDto>builder()
                    .code(-3)
                    .message("Database Error: " + c.getMessage())
                    .data(null)
                    .build();
        }
    }

    public ResponseDto<LoanerDto> updateLoaner(LoanerDto dto, Integer loanerId) {

        Optional<Loaner> optional = loanerRepository.findByLoanerIdAndDeletedAtIsNull(loanerId);
        if (optional.isEmpty()) {
            return ResponseDto.<LoanerDto>builder()
                    .message("Loaner is not found!")
                    .code(-1)
                    .build();
        }

        try {
            Loaner loaner = loanerMapper.toEntity(dto);
            loaner.setLoanerId(optional.get().getLoanerId());
            loaner.setCreatedAt(optional.get().getCreatedAt());
            loaner.setDeletedAt(optional.get().getDeletedAt());
            loaner.setUpdatedAt(LocalDateTime.now());
            loanerRepository.save(loaner);
            return ResponseDto.<LoanerDto>builder()
                    .message(String.format("This is loaner %d id successful updated!", loaner.getLoanerId()))
                    .success(true)
                    .data(loanerMapper.toDto(loaner))
                    .build();
        } catch (Exception e) {
            return ResponseDto.<LoanerDto>builder()
                    .message("Loaner while saving error :: {}" + e.getMessage())
                    .code(-3)
                    .build();
        }
    }

    public ResponseDto<LoanerDto> deleteLoaner(Integer loanerId) {
        Optional<Loaner> optional = loanerRepository.findByLoanerIdAndDeletedAtIsNull(loanerId);
        if (optional.isEmpty()) {
            return ResponseDto.<LoanerDto>builder()
                    .message("Loaner is not found!")
                    .code(-1)
                    .build();
        }
        try {
            Loaner loaner = optional.get();
            loaner.setDeletedAt(LocalDateTime.now());
            loanerRepository.save(loaner);
            return ResponseDto.<LoanerDto>builder()
                    .success(true)
                    .message(String.format("This is loaner %d id successful deleted!", loanerId))
                    .data(loanerMapper.toDto(loaner))
                    .build();
        } catch (Exception e) {
            return ResponseDto.<LoanerDto>builder()
                    .message("Loaner while saving error :: {}" + e.getMessage())
                    .code(-3)
                    .build();
        }
    }

    public ResponseDto<Page<LoanerDto>> getAdventSearch(Map<String, String> params) {
        return null;
    }
}
