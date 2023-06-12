package com.example.simpelproject.service;

import com.example.simpelproject.dto.ErrorDto;
import com.example.simpelproject.dto.ReportsDto;
import com.example.simpelproject.dto.ResponseDto;
import com.example.simpelproject.model.Reports;
import com.example.simpelproject.repository.ReportsRepository;
import com.example.simpelproject.service.mapper.ReportsMapper;
import com.example.simpelproject.service.validation.ReportsValidate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReportsService {

    private final ReportsMapper reportsMapper;
    private final ReportsRepository reportsRepository;
    private final ReportsValidate reportsValidate;

    public ResponseDto<ReportsDto> createReports(ReportsDto dto) {
        try {
            Reports reports = reportsMapper.toEntity(dto);
            reports.setCreatedAt(LocalDateTime.now());
            reportsRepository.save(reports);
            return ResponseDto.<ReportsDto>builder()
                    .message(String.format("This is reports %d id successful created!", reports.getReportsId()))
                    .success(true)
                    .data(reportsMapper.toDto(reports))
                    .build();
        } catch (Exception c) {
            return ResponseDto.<ReportsDto>builder()
                    .code(-3)
                    .message("Reports while saving error: " + c.getMessage())
                    .build();

        }
    }

    public ResponseDto<ReportsDto> getReports(Integer reportsId) {
        try {
            Optional<Reports> optional = reportsRepository.findByReportsIdAndDeletedAtIsNull(reportsId);
            if (optional.isEmpty()) {
                return ResponseDto.<ReportsDto>builder()
                        .message("Reports is not found!")
                        .code(-1)
                        .build();
            }
            return ResponseDto.<ReportsDto>builder()
                    .message("OK")
                    .success(true)
                    .data(reportsMapper.toDto(optional.get()))
                    .build();
        } catch (Exception c) {
            return ResponseDto.<ReportsDto>builder()
                    .code(-3)
                    .message("Database Error: " + c.getMessage())
                    .data(null)
                    .build();
        }
    }

    public ResponseDto<ReportsDto> updateReports(ReportsDto dto, Integer reportsId) {

        Optional<Reports> optional = reportsRepository.findByReportsIdAndDeletedAtIsNull(reportsId);
        if (optional.isEmpty()) {
            return ResponseDto.<ReportsDto>builder()
                    .message("Reports is not found!")
                    .code(-1)
                    .build();
        }

        try {
            Reports reports = reportsMapper.toEntity(dto);
            reports.setReportsId(optional.get().getReportsId());
            reports.setCreatedAt(optional.get().getCreatedAt());
            reports.setDeletedAt(optional.get().getDeletedAt());
            reports.setUpdatedAt(LocalDateTime.now());
            reportsRepository.save(reports);
            return ResponseDto.<ReportsDto>builder()
                    .message(String.format("This is reports %d id successful updated!", reports.getReportsId()))
                    .success(true)
                    .data(reportsMapper.toDto(reports))
                    .build();
        } catch (Exception e) {
            return ResponseDto.<ReportsDto>builder()
                    .message("Reports while saving error :: {}" + e.getMessage())
                    .code(-3)
                    .build();
        }
    }

    public ResponseDto<ReportsDto> deleteReports(Integer reportsId) {
        Optional<Reports> optional = reportsRepository.findByReportsIdAndDeletedAtIsNull(reportsId);
        if (optional.isEmpty()) {
            return ResponseDto.<ReportsDto>builder()
                    .message("Reports is not found!")
                    .code(-1)
                    .build();
        }
        try {
            Reports reports = optional.get();
            reports.setDeletedAt(LocalDateTime.now());
            reportsRepository.save(reports);
            return ResponseDto.<ReportsDto>builder()
                    .success(true)
                    .message(String.format("This is reports %d id successful deleted!", reportsId))
                    .data(reportsMapper.toDto(reports))
                    .build();
        } catch (Exception e) {
            return ResponseDto.<ReportsDto>builder()
                    .message("Reports while saving error :: {}" + e.getMessage())
                    .code(-3)
                    .build();
        }
    }

    public ResponseDto<Page<ReportsDto>> getAdventSearch(Map<String, String> params) {
        return null;
    }
}
