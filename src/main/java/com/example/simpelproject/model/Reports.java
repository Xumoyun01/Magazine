package com.example.simpelproject.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "reports")
public class Reports {
    @Id
    @GeneratedValue(generator = "reports_seq_id")
    @SequenceGenerator(name = "reports_seq_id", sequenceName = "reports_seq_id", allocationSize = 1)
    @Column(name = "reports_id")
    private Integer reportsId;
    @Column(name = "prod_name")
    private String prodName;
    @Column(name = "prod_present")
    private Double prodPresent;

    @OneToMany(mappedBy = "categoryId",cascade = CascadeType.ALL)
    private List<Category> categories;

    @Column(name = ("created_at"))
    private LocalDateTime createdAt;
    @Column(name = ("updated_at"))
    private LocalDateTime updatedAt;
    @Column(name = ("deleted_at"))
    private LocalDateTime deletedAt;
}
