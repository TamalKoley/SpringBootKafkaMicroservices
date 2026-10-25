package com.tamal.kafkaservice.stockservice.Data;

import com.tamal.kafkaservice.basedomains.dto.Order;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "stockdata")
@Getter
@Setter
@NoArgsConstructor
public class StockData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "order_id")
    private  String orderId;
    private  String name;
    private  double price;
    private  int quantity;
    private String message;
    private String status;


    public StockData(Order order) {
        this.orderId = order.getOrderId();
        this.name = order.getName();
        this.price = order.getPrice();
        this.quantity = order.getQuantity();
    }


}
