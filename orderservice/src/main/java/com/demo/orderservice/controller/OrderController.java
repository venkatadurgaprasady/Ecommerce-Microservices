package com.demo.orderservice.controller;


import com.demo.orderservice.dto.OrderRequest;
import com.demo.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")

public class OrderController {
    @Autowired
    private OrderService orderService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String placeorder(@RequestBody OrderRequest orderRequest){
        orderService.placeorder(orderRequest);
        return "Order Placed Successfully";
    }

}
