package com.example.simpelproject.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "foreign_debt")
public class ForeignDebt {
    @Id
    @GeneratedValue(generator = "foreign_seq_id")
    @SequenceGenerator(name = "foreign_seq_id", sequenceName = "foreign_seq_id", allocationSize = 1)
    @Column(name = "foreign_id")
    private Integer foreignId;
    @Column(name = "company_name")
    private String companyName;
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "first_phone_number")
    private String firstPhoneNumber;
    @Column(name = "second_phone_number")
    private String secondPhoneNumber;
    private Boolean status;

    @OneToMany(mappedBy = "productId",cascade = CascadeType.ALL)
    private List<Product> products;

    @Column(name = ("created_at"))
    private LocalDateTime createdAt;
    @Column(name = ("updated_at"))
    private LocalDateTime updatedAt;
    @Column(name = ("deleted_at"))
    private LocalDateTime deletedAt;
}
