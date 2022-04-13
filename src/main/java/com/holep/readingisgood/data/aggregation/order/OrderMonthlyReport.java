package com.holep.readingisgood.data.aggregation.order;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.Month;

@Data
public class OrderMonthlyReport {

    private Integer totalOrderCount;
    private Integer totalBookCount;
    private Double totalPurchasedAmount;

    @JsonIgnore
    transient Integer monthIndex;

    @JsonProperty
    public String getMonth() {

        if (monthIndex == null) {
            return null;
        }

        return Month.of(monthIndex).name();
    }
}
