package com.example.eshop.service;

import com.example.eshop.model.ShoppingCart;
import com.example.eshop.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ShoppingCartService {

    @Autowired
    private final ShoppingCartRepository shoppingCartRepository;

    public ShoppingCartService(ShoppingCartRepository shoppingCartRepository) {
        this.shoppingCartRepository = shoppingCartRepository;

    }

    public ShoppingCart addProductToShoppingCart(ShoppingCart shoppingCart) {
        return shoppingCartRepository.save(shoppingCart);
    }

    public List<ShoppingCart> getAllCartItems(){
        return shoppingCartRepository.findAll();
    }


}
