package com.example.eshop.service;

import com.example.eshop.model.Product;
import com.example.eshop.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class AdminService {


    private final ProductRepository productRepository;

    public AdminService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAllActiveProducts();
    }

    public void deleteByProductName(String productName) {
        productRepository.deleteByProductName(productName);
    }

    public void updateByProductName(String productName, Product updatedProduct) {
        for (Product product : getAllProducts()) {
            if (product.getProductName().equals(productName)) {
                product.setProductName(updatedProduct.getProductName());
                product.setPrice(updatedProduct.getPrice());
                product.setDescription(updatedProduct.getDescription());
                product.setImage(updatedProduct.getImage());
                productRepository.save(product);
            }
        }
    }

    @Transactional
    public void deleteWithTimestamp(String productName) {
        LocalDateTime now = LocalDateTime.now();
        productRepository.markAsDeleted(productName, now);
    }

}
