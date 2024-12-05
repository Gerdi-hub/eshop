package com.example.eshop.repository;
import com.example.eshop.model.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    void deleteByProductName(String productName);

    Optional<ShoppingCart> findByProductName(String productName);
    List<ShoppingCart> findAll();
    @Modifying
    @Transactional
    void deleteAll();
}
