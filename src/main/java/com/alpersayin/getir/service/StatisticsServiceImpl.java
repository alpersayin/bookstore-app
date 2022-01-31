package com.alpersayin.getir.service;

import com.alpersayin.getir.entity.enums.OrderStatus;
import com.alpersayin.getir.payload.response.StatisticResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@Service
@AllArgsConstructor
public class StatisticsServiceImpl implements StatisticsService {
    private final MongoTemplate mongoTemplate;

    @Override
    public List<StatisticResponse> getMonthlyStatistics() {
        Aggregation agg = newAggregation(
                match(new Criteria("status").is(OrderStatus.PURCHASED.name())),
                project("id").andExpression("month(orderDateTime)").as("month"),
                group("month").count().as("totalOrder"),
                project("totalOrder").and("month").previousOperation()
        );

        AggregationResults<StatisticResponse> groupResults
                = mongoTemplate.aggregate(agg, "orders", StatisticResponse.class);
        return groupResults.getMappedResults();
    }

}
