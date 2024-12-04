package com.example.eshop.repository;
import com.example.eshop.model.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    void deleteByProductName(String productName);

    Optional<ShoppingCart> findByProductName(String productName);
}
