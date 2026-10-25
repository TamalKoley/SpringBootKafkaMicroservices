package com.tamal.kafkaservice.stockservice.Kafka;


import com.tamal.kafkaservice.basedomains.dto.OrderEvent;
import com.tamal.kafkaservice.stockservice.Data.StockData;
import com.tamal.kafkaservice.stockservice.Database.StockJPARepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class StockServiceConsumer {

    private final Logger LOGGER = LoggerFactory.getLogger(StockServiceConsumer.class);
    private final StockJPARepository stockJPARepository;

    public StockServiceConsumer(StockJPARepository stockJPARepository) {
        this.stockJPARepository = stockJPARepository;
    }

    @KafkaListener(topics = "${spring.kafka.topic.name}" ,groupId = "${spring.kafka.consumer.group-id}")
    public void consume(OrderEvent message){
        LOGGER.info("Received stock service message:{}",message.toString());
        StockData stockData = new StockData(message.getOrder());
        LOGGER.info("id :{}",stockData.getId());
        stockData.setMessage(message.getMessage());
        stockData.setStatus(message.getStatus());
        stockJPARepository.save(stockData);
        LOGGER.info("Message save in Database");

    }

}
