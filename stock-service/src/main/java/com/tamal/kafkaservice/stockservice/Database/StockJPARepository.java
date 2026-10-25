package com.tamal.kafkaservice.stockservice.Database;

import com.tamal.kafkaservice.stockservice.Data.StockData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockJPARepository extends JpaRepository<StockData,Integer> {
}
