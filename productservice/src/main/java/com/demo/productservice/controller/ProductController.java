package com.demo.productservice.controller;

import com.demo.productservice.DTO.ProductRequest;
import com.demo.productservice.DTO.ProductResponse;
import com.demo.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create_product(@RequestBody ProductRequest productRequest){
        productService.create_product(productRequest);
    }
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> get_allproducts(){
        return productService.get_allproducts();
    }
}
