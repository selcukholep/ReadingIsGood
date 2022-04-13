package com.holep.readingisgood.data.aggregation.order;

import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.DateOperators;
import org.springframework.stereotype.Component;

@Component
public class AggregationMonthlyReport {

    public Aggregation prepare() {
        return Aggregation.newAggregation(
                Aggregation.project("creationDate", "details")
                        .and(DateOperators.Month.monthOf("creationDate")).as("monthIndex"),
                Aggregation.unwind("details"),
                Aggregation.group("monthIndex")
                        .sum("details.amount").as("totalBookCount")
                        .sum("details.price").as("totalPurchasedAmount")
                        .count().as("totalOrderCount"),
                Aggregation.project("totalBookCount", "totalOrderCount", "totalPurchasedAmount")
                        .and("_id").as("monthIndex")
        );
    }
}
