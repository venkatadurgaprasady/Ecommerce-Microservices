package com.demo.productservice.service;

import com.demo.productservice.DTO.ProductRequest;
import com.demo.productservice.DTO.ProductResponse;
import com.demo.productservice.model.Product;
import com.demo.productservice.repository.ProductRespository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductService {
    @Autowired
    private ProductRespository productRespository;
    public void create_product (ProductRequest productRequest){
        Product product=Product.builder().name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();
        productRespository.save(product);
        log.info("Product {} is saved",product.getId());
    }

    public List<ProductResponse> get_allproducts(){
        List<Product> products =productRespository.findAll();
        return products.stream().map(product->mapresponse(product)).collect(Collectors.toList());
    }

    private ProductResponse mapresponse(Product product) {
        return ProductResponse.builder()
                .Id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }

}
