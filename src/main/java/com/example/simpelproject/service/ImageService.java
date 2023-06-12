package com.example.simpelproject.service;

import com.example.simpelproject.dto.ErrorDto;
import com.example.simpelproject.dto.ImageDto;
import com.example.simpelproject.dto.ResponseDto;
import com.example.simpelproject.model.Image;
import com.example.simpelproject.repository.ImageRepository;
import com.example.simpelproject.service.mapper.ImageMapper;
import com.example.simpelproject.service.validation.ImageValidate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageMapper imageMapper;
    private final ImageRepository imageRepository;
    private final ImageValidate imageValidate;

    public ResponseDto<ImageDto> createImage(ImageDto dto) {
        List<ErrorDto> errors = imageValidate.validate(dto);
        if (!errors.isEmpty()){
            return ResponseDto.<ImageDto>builder()
                    .message("Validation error!")
                    .code(-2)
                    .data(dto)
                    .errors(errors)
                    .build();
        }
        try {
            Image image= imageMapper.toEntity(dto);
            image.setCreatedAt(LocalDateTime.now());
            imageRepository.save(image);
            return ResponseDto.<ImageDto>builder()
                    .message(String.format("This is image %d id successful created!",image.getId()))
                    .success(true)
                    .data(imageMapper.toDto(image))
                    .build();
        } catch (Exception c) {
            return ResponseDto.<ImageDto>builder()
                    .code(-3)
                    .message("Image while saving error: " + c.getMessage())
                    .build();

        }
    }

    public ResponseDto<ImageDto> getImage(Integer imageId) {
        try {
            Optional<Image> optional = imageRepository.findByIdAndDeletedAtIsNull(imageId);
            if (optional.isEmpty()) {
                return ResponseDto.<ImageDto>builder()
                        .message("Image is not found!")
                        .code(-1)
                        .build();
            }
            return ResponseDto.<ImageDto>builder()
                    .message("OK")
                    .success(true)
                    .data(imageMapper.toDto(optional.get()))
                    .build();
        } catch (Exception c) {
            return ResponseDto.<ImageDto>builder()
                    .code(-3)
                    .message("Database Error: " + c.getMessage())
                    .data(null)
                    .build();
        }
    }

    public ResponseDto<ImageDto> updateImage(ImageDto dto, Integer imageId) {
        List<ErrorDto> errors = imageValidate.validate(dto);
        if (!errors.isEmpty()){
            return ResponseDto.<ImageDto>builder()
                    .message("Validation error!")
                    .code(-2)
                    .data(dto)
                    .errors(errors)
                    .build();
        }

        Optional<Image> optional = imageRepository.findByIdAndDeletedAtIsNull(imageId);
        if (optional.isEmpty()) {
            return ResponseDto.<ImageDto>builder()
                    .code(-1)
                    .message("Image is not found!")
                    .build();
        }

        try {
            Image image=imageMapper.toEntity(dto);
            image.setId(optional.get().getId());
            image.setCreatedAt(optional.get().getCreatedAt());
            image.setDeletedAt(optional.get().getDeletedAt());
            image.setUpdatedAt(LocalDateTime.now());
            imageRepository.save(image);
            return ResponseDto.<ImageDto>builder()
                    .message(String.format("This is Image %d id successful updated!",image.getId()))
                    .success(true)
                    .data(imageMapper.toDto(image))
                    .build();
        } catch (Exception e) {
            return ResponseDto.<ImageDto>builder()
                    .message("Image while saving error :: {}" + e.getMessage())
                    .code(-3)
                    .build();
        }
    }

    public ResponseDto<ImageDto> deleteImage(Integer imageId) {
        Optional<Image> optional = imageRepository.findByIdAndDeletedAtIsNull(imageId);
        if (optional.isEmpty()) {
            return ResponseDto.<ImageDto>builder()
                    .code(-1)
                    .message("Image is not found!")
                    .build();
        }
        try {
            Image image = optional.get();
            image.setDeletedAt(LocalDateTime.now());
            imageRepository.save(image);
            return ResponseDto.<ImageDto>builder()
                    .success(true)
                    .message(String.format("This is image %d id successful deleted!",imageId))
                    .data(imageMapper.toDto(image))
                    .build();
        } catch (Exception e) {
            return ResponseDto.<ImageDto>builder()
                    .message("Image while saving error :: {}" + e.getMessage())
                    .code(-3)
                    .build();
        }
    }

    public ResponseDto<Page<ImageDto>> getAdventSearch(Map<String, String> params) {
        return null;
    }
}
