package com.example.simpelproject.repository;

import com.example.simpelproject.model.Basket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BasketRepository extends JpaRepository<Basket, Integer> {

    Optional<Basket> findByBasketIdAndDeletedAtIsNull(Integer id);
}
