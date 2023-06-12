package com.example.simpelproject.repository;

import com.example.simpelproject.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<Image, Integer> {

    Optional<Image> findByIdAndDeletedAtIsNull(Integer id);

}
