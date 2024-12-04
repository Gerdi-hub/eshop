package com.example.eshop.controller;


import com.example.eshop.model.ShoppingCart;
import com.example.eshop.service.ShoppingCartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin(origins = "*")
public class CartController {

    private final ShoppingCartService shoppingCartService;


    public CartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @PostMapping("/add-to-cart")
    public ShoppingCart addProductToShoppingCart(@RequestBody ShoppingCart shoppingCart) {
        return shoppingCartService.addProductToShoppingCart(shoppingCart);
    }
    @GetMapping("/shoppingcart")
    public List<ShoppingCart> getAllCartItems(){
        return shoppingCartService.getAllCartItems();
    }

/*    @DeleteMapping("/remove-from-cart")
    public ResponseEntity<String> removeProductFromShoppingCart(
            @RequestParam String productName,
            @RequestParam int quantity) {
        try {
            shoppingCartService.removeProductFromShoppingCart(productName, quantity);
            return ResponseEntity.ok("Product removed from the cart successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }*/

    @GetMapping("/calculate-cart-total")
    public double calculateCartTotal(){
        return shoppingCartService.calculateCartTotal();
    }

    @GetMapping("/cart-items-count")
    public Integer countCartItems(){
        return shoppingCartService.countCartItems();
    }


}
