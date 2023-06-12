package com.example.simpelproject.repository;

import com.example.simpelproject.model.ForeignDebt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ForeignDebtRepository extends JpaRepository<ForeignDebt,Integer> {

    Optional<ForeignDebt> findByForeignIdAndDeletedAtIsNull(Integer id);
}
