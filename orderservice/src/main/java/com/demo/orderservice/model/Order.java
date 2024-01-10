package com.demo.orderservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
@Entity
@Table(name="t_order")

@Data
@AllArgsConstructor@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String ordernumber;
    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderLineItems> orderLineItems;
}
