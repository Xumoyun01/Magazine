package com.example.simpelproject.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(generator = "product_seq_id")
    @SequenceGenerator(name = "product_seq_id", sequenceName = "product_seq_id", allocationSize = 1)
    @Column(name = "product_id")
    private Integer productId;
    @Column(name = "prod_name")
    private String prodName;
    @Column(name = "received_price")
    private Double receivedPrice;
    @Column(name = "prod_mass")
    private Double prodMass;
    @Column(name = "selling_price")
    private Double sellingPrice;
    private Double amount;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "image_id",referencedColumnName = "id", insertable = false, updatable = false)
    private Image image;

    @Column(name = "image_id")
    private Integer imageId;

    @Column(name = "expired_at")
    private LocalDate expiredAt;
    @Column(name = ("created_at"))
    private LocalDateTime createdAt;
    @Column(name = ("updated_at"))
    private LocalDateTime updatedAt;
    @Column(name = ("deleted_at"))
    private LocalDateTime deletedAt;
}
