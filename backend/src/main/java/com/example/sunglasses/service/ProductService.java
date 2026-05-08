package com.example.sunglasses.service;

import com.example.sunglasses.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    public List<Product> getProducts() {

        return List.of(

                new Product(1, "Black Aviator", 1999),
                new Product(2, "Classic Round", 1499),
                new Product(3, "Premium Gold", 2999)

        );
    }
}