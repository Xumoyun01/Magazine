package com.example.simpelproject.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "basket")
public class Basket {

    @Id
    @GeneratedValue(generator = "basket_seq_id")
    @SequenceGenerator(name = "basket_seq_id", sequenceName = "basket_seq_id", allocationSize = 1)
    @Column(name = "basket_id")
    private Integer basketId;
    @Column(name = "prod_mass")
    private Double prodMass;
    @Column(name = "prod_price")
    private Double prodPrice;
    @Column(name = "total_price")
    private Double totalPrice;

    @OneToMany(mappedBy = "productId", cascade = CascadeType.ALL)
    private List<Product> products;

    @Column(name = ("created_at"))
    private LocalDateTime createdAt;
    @Column(name = ("updated_at"))
    private LocalDateTime updatedAt;
    @Column(name = ("deleted_at"))
    private LocalDateTime deletedAt;
}
