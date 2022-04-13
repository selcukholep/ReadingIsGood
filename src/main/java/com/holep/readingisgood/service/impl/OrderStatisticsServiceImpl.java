package com.holep.readingisgood.service.impl;

import com.holep.readingisgood.data.aggregation.order.AggregationMonthlyReport;
import com.holep.readingisgood.data.aggregation.order.OrderMonthlyReport;
import com.holep.readingisgood.data.entity.Order;
import com.holep.readingisgood.service.OrderStatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.DateOperators;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderStatisticsServiceImpl implements OrderStatisticsService {

    final AggregationMonthlyReport aggregationMonthlyReport;
    final MongoTemplate mongoTemplate;

    @Override
    public List<OrderMonthlyReport> getMonthlyReport() {

        AggregationResults<OrderMonthlyReport> result = mongoTemplate.aggregate(
                aggregationMonthlyReport.prepare(), Order.class, OrderMonthlyReport.class);
        return result.getMappedResults();
    }
}
