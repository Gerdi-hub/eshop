package com.example.eshop.model;


import jakarta.persistence.*;
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

    @Column(name = "product_name")
    private String productName;
    private int quantity;
    private double price;

}
