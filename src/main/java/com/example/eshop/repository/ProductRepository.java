package com.example.eshop.repository;

import com.example.eshop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
        void deleteByProductName(String productName);
        @Modifying
        @Query("UPDATE Product p SET p.deletedAt = :timestamp WHERE p.productName = :productName")
        void markAsDeleted(@Param("productName") String productName, @Param("timestamp") LocalDateTime timestamp);

        // Custom query to exclude deleted products
        @Query("SELECT p FROM Product p WHERE p.deletedAt IS NULL")
        List<Product> findAllActiveProducts();

}
