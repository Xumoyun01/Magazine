package com.example.simpelproject.service;

import com.example.simpelproject.dto.CategoryDto;
import com.example.simpelproject.dto.ErrorDto;
import com.example.simpelproject.dto.ResponseDto;
import com.example.simpelproject.model.Category;
import com.example.simpelproject.repository.CategoryRepository;
import com.example.simpelproject.service.mapper.CategoryMapper;
import com.example.simpelproject.service.validation.CategoryValidate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryMapper categoryMapper;
    private final CategoryRepository categoryRepository;
    private final CategoryValidate categoryValidate;

    public ResponseDto<CategoryDto> createCategory(CategoryDto dto) {
        try {
            Category category = categoryMapper.toEntity(dto);
            category.setCreatedAt(LocalDateTime.now());
            categoryRepository.save(category);
            return ResponseDto.<CategoryDto>builder()
                    .message(String.format("This is category %d id successful created!", category.getCategoryId()))
                    .success(true)
                    .data(categoryMapper.toDto(category))
                    .build();
        } catch (Exception c) {
            return ResponseDto.<CategoryDto>builder()
                    .code(-3)
                    .message("Category while saving error: " + c.getMessage())
                    .build();

        }
    }

    public ResponseDto<CategoryDto> getCategory(Integer categoryId) {
        try {
            Optional<Category> optional = categoryRepository.findByCategoryIdAndDeletedAtIsNull(categoryId);
            if (optional.isEmpty()) {
                return ResponseDto.<CategoryDto>builder()
                        .message("Category is not found!")
                        .code(-1)
                        .build();
            }
            return ResponseDto.<CategoryDto>builder()
                    .message("OK")
                    .success(true)
                    .data(categoryMapper.toDto(optional.get()))
                    .build();
        } catch (Exception c) {
            return ResponseDto.<CategoryDto>builder()
                    .code(-3)
                    .message("Database Error: " + c.getMessage())
                    .data(null)
                    .build();
        }
    }

    public ResponseDto<CategoryDto> updateCategory(CategoryDto dto, Integer categoryId) {
        Optional<Category> optional = categoryRepository.findByCategoryIdAndDeletedAtIsNull(categoryId);
        if (optional.isEmpty()) {
            return ResponseDto.<CategoryDto>builder()
                    .code(-1)
                    .message("Category is not found!")
                    .build();
        }

        try {
            Category category = categoryMapper.toEntity(dto);
            category.setCategoryId(optional.get().getCategoryId());
            category.setCreatedAt(optional.get().getCreatedAt());
            category.setDeletedAt(optional.get().getDeletedAt());
            category.setUpdatedAt(LocalDateTime.now());
            categoryRepository.save(category);
            return ResponseDto.<CategoryDto>builder()
                    .message(String.format("This is category %d id successful updated!", category.getCategoryId()))
                    .success(true)
                    .data(categoryMapper.toDto(category))
                    .build();
        } catch (Exception e) {
            return ResponseDto.<CategoryDto>builder()
                    .message("Category while saving error :: {}" + e.getMessage())
                    .code(-3)
                    .build();
        }
    }

    public ResponseDto<CategoryDto> deleteCategory(Integer categoryId) {
        Optional<Category> optional = categoryRepository.findByCategoryIdAndDeletedAtIsNull(categoryId);
        if (optional.isEmpty()) {
            return ResponseDto.<CategoryDto>builder()
                    .code(-1)
                    .message("Category is not found!")
                    .build();
        }
        try {
            Category category = optional.get();
            category.setDeletedAt(LocalDateTime.now());
            categoryRepository.save(category);
            return ResponseDto.<CategoryDto>builder()
                    .success(true)
                    .message(String.format("This is category %d id successful deleted!", categoryId))
                    .data(categoryMapper.toDto(category))
                    .build();
        } catch (Exception e) {
            return ResponseDto.<CategoryDto>builder()
                    .message("Category while saving error :: {}" + e.getMessage())
                    .code(-3)
                    .build();
        }
    }

    public ResponseDto<Page<CategoryDto>> getAdventSearch(Map<String, String> params) {
        return null;
    }
}
