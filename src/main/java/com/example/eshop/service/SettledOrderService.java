package com.example.eshop.service;


import com.example.eshop.dto.SettledOrderDto;
import com.example.eshop.model.SettledOrder;
import com.example.eshop.model.ShoppingCart;
import com.example.eshop.model.User;
import com.example.eshop.repository.OrdersRepository;
import com.example.eshop.repository.ShoppingCartRepository;
import com.example.eshop.repository.UserRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Getter
@Setter
@Service
public class SettledOrderService {

    private final ShoppingCartRepository shoppingCartRepository;
    private final OrdersRepository ordersRepository;
    private final UserRepository userRepository;

    public SettledOrderService(ShoppingCartRepository shoppingCartRepository, OrdersRepository ordersRepository, UserRepository userRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.ordersRepository = ordersRepository;
        this.userRepository = userRepository;
    }


    public List<SettledOrderDto> getSettledOrderDtos() {
        List<SettledOrderDto> settledOrderDtos = new ArrayList<>();

        List<SettledOrder> settledOrders = ordersRepository.findAll();
        for (SettledOrder settledOrder : settledOrders) {
            Optional<User> user = userRepository.findByUsername(settledOrder.getUsername());
            if (user.isPresent()) {
                settledOrderDtos.add(new SettledOrderDto(settledOrder, user.get()));
            } else {
                settledOrderDtos.add(new SettledOrderDto(settledOrder, null));
            }
        }
        return settledOrderDtos;
    }

    @Transactional
    public void addOrdersFromCartToSettledOrdersTable(String username) {
        // Step 1: Fetch all items from the shopping cart
        List<ShoppingCart> cartItems = shoppingCartRepository.findAll();
        if (cartItems.isEmpty()) {
            throw new IllegalStateException("Shopping cart is empty!");
        }

        // Step 2: Find the highest existing order number and determine the next new order number
        Long highestOrderNumber = ordersRepository.findMaxOrderNumber();
        Long newOrderNumber = (highestOrderNumber == null) ? 1L : (highestOrderNumber + 1);  // Next order number for the shopping cart

        // Step 3: Get current date and time for order date
        Date currentDate = new Date();

        // Step 4: Create a list to hold settled orders for the current cart
        List<SettledOrder> settledOrders = new ArrayList<>();

        for (ShoppingCart cartItem : cartItems) {
            SettledOrder settledOrder = new SettledOrder();
            settledOrder.setNewOrderNumber(newOrderNumber);  // Set the same newOrderNumber for all cart items
            settledOrder.setOrderDate(currentDate);  // Set the order date
            settledOrder.setProductName(cartItem.getProductName());  // Set the product name
            settledOrder.setQuantity(cartItem.getQuantity());  // Set the quantity
            settledOrder.setPrice(cartItem.getPrice());// Set the price
            settledOrder.setUsername(username);

            settledOrders.add(settledOrder);  // Add the order to the list
        }

        // Step 5: Save all settled orders to the database
        ordersRepository.saveAll(settledOrders);
    }

    @Transactional
    public List<SettledOrder> getLastSettledOrders() {
        // Step 1: Find the highest newOrderNumber
        Long highestOrderNumber = ordersRepository.findMaxOrderNumber2();
        if (highestOrderNumber == null) {
            throw new IllegalStateException("No orders found!");
        }
        // Step 2: Fetch all orders with the highest newOrderNumber
        return ordersRepository.findByNewOrderNumber(highestOrderNumber);
    }

        public void deleteFromCart() {
            // Step 6: Clear the shopping cart
            shoppingCartRepository.deleteAll();
        }
    }


