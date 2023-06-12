package com.example.simpelproject.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductBaseDto {

    private Integer prodBaseId;
    private String prodName;
    private Double receivedPrice;
    private Double sellingPrice;
    private Double prodMass;
    private Double amount;

}
