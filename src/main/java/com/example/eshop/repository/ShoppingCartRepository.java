package com.example.eshop.repository;
import com.example.eshop.model.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;



public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {

}
