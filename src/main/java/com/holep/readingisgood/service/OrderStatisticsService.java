package com.holep.readingisgood.service;

import com.holep.readingisgood.data.aggregation.order.OrderMonthlyReport;

import java.util.List;

public interface OrderStatisticsService {

    List<OrderMonthlyReport> getMonthlyReport();
}
