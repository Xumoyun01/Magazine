package com.example.simpelproject.repository;

import com.example.simpelproject.model.Loaner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoanerRepository extends JpaRepository<Loaner,Integer> {

    Optional<Loaner> findByLoanerIdAndDeletedAtIsNull(Integer id);

}
