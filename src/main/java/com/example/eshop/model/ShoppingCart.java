package com.example.eshop.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@NoArgsConstructor              //klassi annotatsioonid
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name ="shopping_cart")
@ToString


public class ShoppingCart {

    @Id
    @GeneratedValue
    private long id;
    private String productName;
    private int quantity;
    private double price;

}
