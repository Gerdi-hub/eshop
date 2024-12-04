package com.example.eshop.service;

import com.example.eshop.model.ShoppingCart;
import com.example.eshop.repository.ShoppingCartRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ShoppingCartService {

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

  /*  @Transactional
    public void removeProductFromShoppingCart(String productName, int quantityToRemove) {
        // Find the product in the shopping cart by productName
        Optional<ShoppingCart> cartItemOptional = shoppingCartRepository.findAll().stream()
                .filter(item -> item.getProductName().equalsIgnoreCase(productName))
                .findFirst();

        if (cartItemOptional.isPresent()) {
            ShoppingCart cartItem = cartItemOptional.get();

            // Reduce the quantity or remove the item if quantity becomes zero
            if (cartItem.getQuantity() > quantityToRemove) {
                cartItem.setQuantity(cartItem.getQuantity() - quantityToRemove);
                shoppingCartRepository.save(cartItem);
            } else {
                shoppingCartRepository.delete(cartItem);
            }
        } else {
            throw new IllegalArgumentException("Product with name " + productName + " not found in the shopping cart.");
        }
    }*/

    public Double calculateCartTotal(){
        List<ShoppingCart> cartItems=getAllCartItems();
        Double total=0.0;
        for(ShoppingCart cartItem:cartItems){
            total+=cartItem.getPrice()*cartItem.getQuantity();

        }
        System.out.println(total);
        return total;
    }
    public Integer countCartItems(){
        int itemsCount= 0;
        for(ShoppingCart cartItem:getAllCartItems()){
            itemsCount+=cartItem.getQuantity();
        }
        return itemsCount;
    }



}
