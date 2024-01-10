package org.notificationsender;

import org.notificationsender.dto.OrderResponse;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.KafkaListeners;

@SpringBootApplication
@EnableDiscoveryClient
public class NotificationSender {
    public static void main(String[] args) {
        SpringApplication.run(NotificationSender.class, args);
    }
   @KafkaListener(topics="notificationTopic")
    public void subscribeNotification(OrderResponse orderResponse) {
        //send email notification implementation
        System.out.println("Received the order with" + orderResponse.getOrdernumber());
    }
}