package com.demo.orderservice.service;

import com.demo.orderservice.dto.InventoryResponse;
import com.demo.orderservice.dto.NotificationSender;
import com.demo.orderservice.dto.OrderLineItemsdto;
import com.demo.orderservice.dto.OrderRequest;
import com.demo.orderservice.model.Order;
import com.demo.orderservice.model.OrderLineItems;
import com.demo.orderservice.repository.OrderRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {
    @Autowired
    private OrderRespository orderRespository;
    @Autowired
    private WebClient.Builder webClient;
    @Autowired
    private KafkaTemplate<String, NotificationSender> kafkaTemplate;
    public void placeorder(OrderRequest orderRequest){
        Order order= new Order();
        order.setOrdernumber(UUID.randomUUID().toString());
        List<OrderLineItems> s=orderRequest.getOrderLineItemsdto().stream().map(this::maptodto).toList();
        order.setOrderLineItems(s);
        List<String> skucodes=order.getOrderLineItems().stream().map(OrderLineItems::getSkucode).toList();
        //Checking with inventory service
        InventoryResponse[] inventoryResponses=webClient.build().get().uri("http://inventory-service/api/inventory",
                uriBuilder -> uriBuilder.queryParam("skucode",skucodes).build())
                .retrieve().bodyToMono(InventoryResponse[].class).block();
        boolean placeorder=Arrays.stream(inventoryResponses).allMatch(InventoryResponse::isInStock);
        if(placeorder){
            orderRespository.save(order);
            kafkaTemplate.send("notificationTopic", new NotificationSender(order.getOrdernumber()));
        }
        else{
            throw new IllegalArgumentException("Products out of stock.Please try gain later");
        }

    }
    public OrderLineItems maptodto(OrderLineItemsdto orderLineItemsdto){
        OrderLineItems odt=new OrderLineItems();
        odt.setQuantity(orderLineItemsdto.getQuantity());
        odt.setPrice(orderLineItemsdto.getPrice());
        odt.setSkucode(orderLineItemsdto.getSkucode());
        return odt;

    }
}

