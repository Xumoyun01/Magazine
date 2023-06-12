package com.example.simpelproject.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;


@Getter
@Setter
@Entity
@Table(name = ("users"))
public class User {
    @Id
    @GeneratedValue(generator = "user_seq_id")
    @SequenceGenerator(name = "user_seq_id", sequenceName = "user_seq_id", allocationSize = 1)
    private Integer id;
    @Column(name = ("first_name"))
    private String firstName;
    @Column(name = ("last_name"))
    private String lastName;
    @Column(name = ("middle_name"))
    private String middleName;
    @Column(name = ("user_name"))
    private String userName;
    @Column(name = ("borrow_name"))
    private String borrowName;
    @Column(name = ("phone_number"))
    private String phoneNumber;
    @Column(name = ("passport_series"))
    private String passportSeries;
    @Column(name = ("first_address"))
    private String firstAddress;
    @Column(name = ("birth_date"))
    private LocalDate birthDate;
    @Column(name = ("monthly_price"))
    private Double monthlyPrice;
    @Column(name = ("working_time"))
    private LocalDate workingTime;
    @Column(name = ("working_days"))
    private LocalDate workingDays;
    @Column(name = ("created_at"))
    private LocalDateTime createdAt;
    @Column(name = ("updated_at"))
    private LocalDateTime updatedAt;
    @Column(name = ("deleted_at"))
    private LocalDateTime deletedAt;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "loaner_id", referencedColumnName = "loaner_id",insertable = false,updatable = false)
    private Loaner loaners;

    @Column(name = "loaner_id")
    private Integer loanersId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "image_id", referencedColumnName = "id",insertable = false,updatable = false)
    private Image images;
    @Column(name = "image_id")
    private Integer imageId;


}
