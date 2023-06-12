package com.example.simpelproject.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = ("image"))
public class Image {
    @Id
    @GeneratedValue(generator = "image_seq_id")
    @SequenceGenerator(name = "image_seq_id", sequenceName = "image_seq_id", allocationSize = 1)
    private Integer id;
    private String path;
    private String type;
    private Integer size;
    private String token;

    @Column(name = ("created_at"))
    private LocalDateTime createdAt;
    @Column(name = ("updated_at"))
    private LocalDateTime updatedAt;
    @Column(name = ("deleted_at"))
    private LocalDateTime deletedAt;


}
