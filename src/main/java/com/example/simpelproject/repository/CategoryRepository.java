package com.example.simpelproject.repository;

import com.example.simpelproject.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Optional<Category> findByCategoryIdAndDeletedAtIsNull(Integer id);
}
