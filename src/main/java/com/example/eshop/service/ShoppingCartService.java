package com.example.eshop.service;

import com.example.eshop.model.ShoppingCart;
import com.example.eshop.repository.ShoppingCartRepository;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;


@Service
public class ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;


    public ShoppingCartService(ShoppingCartRepository shoppingCartRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
    }

    public ShoppingCart addProductToShoppingCart(ShoppingCart shoppingCart) {
        // Check if the product already exists in the cart
        Optional<ShoppingCart> existingCartItem = shoppingCartRepository.findByProductName(shoppingCart.getProductName());

        if (existingCartItem.isPresent()) {
            // Product already exists, increment the quantity
            ShoppingCart existingItem = existingCartItem.get();
            existingItem.setQuantity(existingItem.getQuantity() + 1);
            return shoppingCartRepository.save(existingItem);
        } else {
            // Product does not exist, add new product to the cart
            return shoppingCartRepository.save(shoppingCart);
        }
    }
    public List<ShoppingCart> getAllCartItems() {
        return shoppingCartRepository.findAll();
    }


    public void removeProductFromShoppingCart(String productName) {
        Optional<ShoppingCart> existingCartItem = shoppingCartRepository.findByProductName(productName);

        if (existingCartItem.isPresent()) {
            ShoppingCart existingItem = existingCartItem.get();

            if (existingItem.getQuantity() > 1) {
                // Decrement the quantity by 1
                existingItem.setQuantity(existingItem.getQuantity() - 1);
                shoppingCartRepository.save(existingItem);
            } else {
                // Quantity is 1, remove the product from the cart
                shoppingCartRepository.delete(existingItem);
            }
        }
    }

    public Double calculateCartTotal() {
        List<ShoppingCart> cartItems = getAllCartItems();
        Double total = 0.0;
        for (ShoppingCart cartItem : cartItems) {
            total += cartItem.getPrice() * cartItem.getQuantity();

        }
        return total;
    }

    public Integer countCartItems() {
        int itemsCount = 0;
        for (ShoppingCart cartItem : getAllCartItems()) {
            itemsCount += cartItem.getQuantity();
        }
        return itemsCount;
    }

}
