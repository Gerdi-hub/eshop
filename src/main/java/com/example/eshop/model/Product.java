package com.example.eshop.model;


import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor              //klassi annotatsioonid
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "products")
@ToString

public class Product {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "productname")
    private String productName;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Double price;

    @Column(name = "image")
    private String image;

}
