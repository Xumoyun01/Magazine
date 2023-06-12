package com.example.simpelproject.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "loaner")
public class Loaner {

    @Id
    @GeneratedValue(generator = "loaner_seq_id")
    @SequenceGenerator(name = "loaner_seq_id", sequenceName = "loaner_seq_id", allocationSize = 1)
    @Column(name = "loaner_id")
    private Integer loanerId;
    @Column(name = "total_price")
    private Double totalPrice;
    private Boolean status;

    @OneToMany(mappedBy = "basketId",cascade = CascadeType.ALL)
    private Set<Basket> baskets;

    @Column(name = ("created_at"))
    private LocalDateTime createdAt;
    @Column(name = ("updated_at"))
    private LocalDateTime updatedAt;
    @Column(name = ("deleted_at"))
    private LocalDateTime deletedAt;
}
