package com.example.eshop.controller;


import com.example.eshop.model.Product;
import com.example.eshop.service.AdminService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService ) {
        this.adminService = adminService;

    }

    @PostMapping("/add-product")
    public Product addProduct(@RequestBody Product product) {
        return adminService.addProduct(product);
    }

    @GetMapping("/show-all-products")
    public List<Product> getAllProducts() {
       return adminService.getAllProducts();
    }

    @DeleteMapping("/remove-product/{productName}")
    public void deleteByProductName(@PathVariable ("productName") String productName) {
        adminService.deleteByProductName(productName);
    }

    @PutMapping("/update-product-details/{productName}")
    public void updateProductDetails(@PathVariable ("productName") String productName, @RequestBody Product product) {
        adminService.updateByProductName(productName, product);
    }
}

