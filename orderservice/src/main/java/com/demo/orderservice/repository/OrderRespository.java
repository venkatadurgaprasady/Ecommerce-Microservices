package com.demo.orderservice.repository;

import com.demo.orderservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRespository extends JpaRepository<Order,Long> {
}
