package com.example.eshop.controller;


import com.example.eshop.model.ShoppingCart;
import com.example.eshop.repository.ShoppingCartRepository;
import com.example.eshop.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin(origins = "*")
public class CartController {

    @Autowired
    private final ShoppingCartService shoppingCartService;


    public CartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @PostMapping("/add-to-cart")
    public ShoppingCart addProductToShoppingCart(@RequestBody ShoppingCart shoppingCart) {
        return shoppingCartService.addProductToShoppingCart(shoppingCart);
    }

}
