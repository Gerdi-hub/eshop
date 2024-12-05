package com.example.eshop.repository;

import com.example.eshop.model.SettledOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrdersRepository extends JpaRepository<SettledOrder, Long>  {
    @Query("SELECT MAX(orderNumber) FROM SettledOrder")
    Long findMaxOrderNumber();
    @Query("SELECT MAX(s.newOrderNumber) FROM SettledOrder s")
    Long findMaxOrderNumber2();

    List<SettledOrder> findByNewOrderNumber(Long highestOrderNumber);
}
