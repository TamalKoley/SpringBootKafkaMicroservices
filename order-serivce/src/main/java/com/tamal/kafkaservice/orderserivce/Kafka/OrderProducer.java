package com.tamal.kafkaservice.orderserivce.Kafka;

import com.tamal.kafkaservice.basedomains.dto.OrderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class OrderProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderProducer.class);
    @Value("${spring.kafka.topic.name}")
    private String topicName;
    private  final KafkaTemplate<String, OrderEvent> kafkaTemplate;



    public OrderProducer(KafkaTemplate<String, OrderEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendOrderEvent(OrderEvent orderEvent) {

        LOGGER.info("order event send => {}",orderEvent.toString());
        Message<OrderEvent> message = MessageBuilder.
                withPayload(orderEvent).
                setHeader(KafkaHeaders.TOPIC, topicName).
                build();
        kafkaTemplate.send(message);
    }
}
