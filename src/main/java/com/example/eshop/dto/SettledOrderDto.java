package com.example.eshop.dto;

import com.example.eshop.model.SettledOrder;
import com.example.eshop.model.User;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class SettledOrderDto {
    private Long orderNumber;
    private Long newOrderNumber;
    private Date orderDate;
    private String productName;
    private int quantity;
    private double price;
    private boolean newOrder;
    private String username;
    private String firstName;
    private String lastName;
    private String email;

    public SettledOrderDto(SettledOrder settledOrder, User user) {
        this.orderNumber = settledOrder.getOrderNumber();
        this.newOrderNumber = settledOrder.getNewOrderNumber();
        this.orderDate = settledOrder.getOrderDate();
        this.productName = settledOrder.getProductName();
        this.quantity = settledOrder.getQuantity();
        this.price = settledOrder.getPrice();
        this.newOrder = settledOrder.isNewOrder();
        this.username = settledOrder.getUsername();
        this.firstName = user != null ? user.getFirstName() : null;
        this.lastName = user != null ? user.getLastName() : null;
        this.email = user != null ? user.getEmail() : null;
    }
}
