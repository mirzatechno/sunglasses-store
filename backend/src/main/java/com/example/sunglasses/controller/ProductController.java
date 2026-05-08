package com.example.sunglasses.controller;

import com.example.sunglasses.model.Product;
import com.example.sunglasses.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin("*")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getProducts() {

        return productService.getProducts();
    }
}