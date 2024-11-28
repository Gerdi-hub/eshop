package com.example.eshop.repository;

import com.example.eshop.model.SettledOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository<SettledOrder, Long>  {
}
