package com.example.eshop.controller;

import com.example.eshop.model.SettledOrder;
import com.example.eshop.service.SettledOrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/settled-orders")
@CrossOrigin(origins ="*")
public class SettledOrderController {

    private final SettledOrderService settledOrderService;
    public SettledOrderController(SettledOrderService settledOrderService) {
        this.settledOrderService = settledOrderService;
    }


    @GetMapping("/all-settled-orders")
    public List<SettledOrder> getSettledOrders() {
        return settledOrderService.getSettledOrders();
    }
    //Kõik orderid admin vaatesse

    @PostMapping("/add-from-cart")
    public ResponseEntity<String> addOrdersFromCart() {
        try {
            settledOrderService.addOrdersFromCartToSettledOrdersTable();
            return ResponseEntity.ok("Orders added successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    //Lisab tooted shopping cartist settled orders tabelisse. tekitab uue newOrderNumber muutuja, mille järgi saab adnmed tellimusele.


    @GetMapping("/last-settled-order")
    public List<SettledOrder> getLastSettledOrders() {
        return settledOrderService.getLastSettledOrders();
    }

    @DeleteMapping("/delete-from-cart")
    public void deleteFromCart() {
        settledOrderService.deleteFromCart();
    }

}


