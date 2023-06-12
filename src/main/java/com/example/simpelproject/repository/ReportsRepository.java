package com.example.simpelproject.repository;

import com.example.simpelproject.model.Reports;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReportsRepository extends JpaRepository<Reports,Integer> {

    Optional<Reports> findByReportsIdAndDeletedAtIsNull(Integer id);
}
