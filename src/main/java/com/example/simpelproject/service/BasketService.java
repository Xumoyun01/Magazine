package com.example.simpelproject.service;

import com.example.simpelproject.dto.BasketDto;
import com.example.simpelproject.dto.CategoryDto;
import com.example.simpelproject.dto.ErrorDto;
import com.example.simpelproject.dto.ResponseDto;
import com.example.simpelproject.model.Basket;
import com.example.simpelproject.repository.BasketRepository;
import com.example.simpelproject.service.mapper.BasketMapper;
import com.example.simpelproject.service.validation.BasketValidate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BasketService {

    private final BasketMapper basketMapper;
    private final BasketRepository basketRepository;
    private final BasketValidate basketValidate;

    public ResponseDto<BasketDto> createBasket(BasketDto dto) {
        try {
            Basket basket = basketMapper.toEntity(dto);
            basket.setCreatedAt(LocalDateTime.now());
            basketRepository.save(basket);
            return ResponseDto.<BasketDto>builder()
                    .message(String.format("This is basket %d id successful created!", basket.getBasketId()))
                    .success(true)
                    .data(basketMapper.toDto(basket))
                    .build();
        } catch (Exception c) {
            return ResponseDto.<BasketDto>builder()
                    .code(-3)
                    .message("Basket while saving error: " + c.getMessage())
                    .build();

        }
    }

    public ResponseDto<BasketDto> getBasket(Integer basketId) {
        try {
            Optional<Basket> optional = basketRepository.findByBasketIdAndDeletedAtIsNull(basketId);
            if (optional.isEmpty()) {
                return ResponseDto.<BasketDto>builder()
                        .message("Basket is not found!")
                        .code(-1)
                        .build();
            }
            return ResponseDto.<BasketDto>builder()
                    .message("OK")
                    .success(true)
                    .data(basketMapper.toDto(optional.get()))
                    .build();
        } catch (Exception c) {
            return ResponseDto.<BasketDto>builder()
                    .code(-3)
                    .message("Database Error: " + c.getMessage())
                    .data(null)
                    .build();
        }
    }

    public ResponseDto<BasketDto> updateBasket(BasketDto dto, Integer basketId) {
        Optional<Basket> optional = basketRepository.findByBasketIdAndDeletedAtIsNull(basketId);
        if (optional.isEmpty()) {
            return ResponseDto.<BasketDto>builder()
                    .message("Basket is not found!")
                    .code(-1)
                    .build();
        }

        try {
            Basket basket = basketMapper.toEntity(dto);
            basket.setBasketId(optional.get().getBasketId());
            basket.setCreatedAt(optional.get().getCreatedAt());
            basket.setDeletedAt(optional.get().getDeletedAt());
            basket.setUpdatedAt(LocalDateTime.now());
            basketRepository.save(basket);
            return ResponseDto.<BasketDto>builder()
                    .message(String.format("This is basket %d id successful updated!", basket.getBasketId()))
                    .success(true)
                    .data(basketMapper.toDto(basket))
                    .build();
        } catch (Exception e) {
            return ResponseDto.<BasketDto>builder()
                    .message("Basket while saving error :: {}" + e.getMessage())
                    .code(-3)
                    .build();
        }
    }

    public ResponseDto<BasketDto> deleteBasket(Integer basketId) {
        Optional<Basket> optional = basketRepository.findByBasketIdAndDeletedAtIsNull(basketId);
        if (optional.isEmpty()) {
            return ResponseDto.<BasketDto>builder()
                    .message("Basket is not found!")
                    .code(-1)
                    .build();
        }
        try {
            Basket basket = optional.get();
            basket.setDeletedAt(LocalDateTime.now());
            basketRepository.save(basket);
            return ResponseDto.<BasketDto>builder()
                    .success(true)
                    .message(String.format("This is basket %d id successful deleted!", basketId))
                    .data(basketMapper.toDto(basket))
                    .build();
        } catch (Exception e) {
            return ResponseDto.<BasketDto>builder()
                    .message("Basket while saving error :: {}" + e.getMessage())
                    .code(-3)
                    .build();
        }
    }

    public ResponseDto<Page<BasketDto>> getAdventSearch(Map<String, String> params) {
        return null;
    }
}
