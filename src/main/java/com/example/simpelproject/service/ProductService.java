package com.example.simpelproject.service;

import com.example.simpelproject.dto.*;
import com.example.simpelproject.model.Product;
import com.example.simpelproject.repository.ProductRepository;
import com.example.simpelproject.service.mapper.ProductMapper;
import com.example.simpelproject.service.validation.ProductValidate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductMapper productMapper;
    private final ProductRepository productRepository;
    private final ProductValidate productValidate;

    public ResponseDto<ProductDto> createProduct(ProductDto dto) {
        try {
            Product product = productMapper.toEntity(dto);
            product.setCreatedAt(LocalDateTime.now());
            productRepository.save(product);
            return ResponseDto.<ProductDto>builder()
                    .success(true)
                    .message("Product successful created!")
                    .data(productMapper.toDto(product))
                    .build();
        } catch (Exception e) {
            return ResponseDto.<ProductDto>builder()
                    .message("Product while saving error :: " + e.getMessage())
                    .code(-1)
                    .build();
        }
    }

    public ResponseDto<ProductDto> getProduct(Integer productId) {
        try {
            Optional<Product> optional = productRepository.findByProductIdAndDeletedAtIsNull(productId);
            if (optional.isEmpty()) {
                return ResponseDto.<ProductDto>builder()
                        .message("Product is not found!")
                        .code(-1)
                        .build();
            }
            return ResponseDto.<ProductDto>builder()
                    .message("OK")
                    .success(true)
                    .data(productMapper.toDto(optional.get()))
                    .build();
        } catch (Exception c) {
            return ResponseDto.<ProductDto>builder()
                    .code(-3)
                    .message("Database Error: " + c.getMessage())
                    .data(null)
                    .build();
        }
    }

    public ResponseDto<ProductDto> updateProduct(ProductDto dto, Integer productId) {

        Optional<Product> optional = productRepository.findByProductIdAndDeletedAtIsNull(productId);
        if (optional.isEmpty()) {
            return ResponseDto.<ProductDto>builder()
                    .code(-1)
                    .message("Product is not found!")
                    .build();
        }

        try {
            Product product = productMapper.toEntity(dto);
            product.setProductId(optional.get().getProductId());
            product.setCreatedAt(optional.get().getCreatedAt());
            product.setDeletedAt(optional.get().getDeletedAt());
            product.setUpdatedAt(LocalDateTime.now());
            productRepository.save(product);
            return ResponseDto.<ProductDto>builder()
                    .message(String.format("This is Product %d id successful updated!", product.getProductId()))
                    .success(true)
                    .data(productMapper.toDto(product))
                    .build();
        } catch (Exception e) {
            return ResponseDto.<ProductDto>builder()
                    .message("Product while saving error :: {}" + e.getMessage())
                    .code(-3)
                    .build();
        }
    }

    public ResponseDto<ProductDto> deleteProduct(Integer productId) {
        Optional<Product> optional = productRepository.findByProductIdAndDeletedAtIsNull(productId);
        if (optional.isEmpty()) {
            return ResponseDto.<ProductDto>builder()
                    .code(-1)
                    .message("Product is not found!")
                    .build();
        }
        try {
            Product product = optional.get();
            product.setDeletedAt(LocalDateTime.now());
            productRepository.save(product);
            return ResponseDto.<ProductDto>builder()
                    .success(true)
                    .message(String.format("This is product %d id successful deleted!", productId))
                    .data(productMapper.toDto(product))
                    .build();
        } catch (Exception e) {
            return ResponseDto.<ProductDto>builder()
                    .message("Product while saving error :: {}" + e.getMessage())
                    .code(-3)
                    .build();
        }
    }

    public ResponseDto<Page<ProductDto>> getAdventSearch(Map<String, String> params) {
        return null;
    }
}
