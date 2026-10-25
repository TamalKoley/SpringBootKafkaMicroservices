package com.tamal.kafkaservice.orderserivce.controller;

import com.tamal.kafkaservice.basedomains.dto.Order;
import com.tamal.kafkaservice.basedomains.dto.OrderEvent;
import com.tamal.kafkaservice.orderserivce.Kafka.OrderProducer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class OrderController {

    private final OrderProducer orderProducer;

    public OrderController(OrderProducer orderProducer) {
        this.orderProducer = orderProducer;
    }

    @PostMapping("/orders")
    public String  placeOrders(@RequestBody Order order){
        order.setOrderId(UUID.randomUUID().toString());
        OrderEvent orderEvent = new OrderEvent();
        orderEvent.setOrder(order);
        orderEvent.setStatus("Pending");
        orderEvent.setMessage("Order Placed Successfully");
        orderProducer.sendOrderEvent(orderEvent);
        return "Order Placed";
    }

}
