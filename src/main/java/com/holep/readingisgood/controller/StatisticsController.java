package com.holep.readingisgood.controller;

import com.holep.readingisgood.data.aggregation.order.OrderMonthlyReport;
import com.holep.readingisgood.service.OrderStatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/statistics")
@RequiredArgsConstructor
public class StatisticsController {

    final OrderStatisticsService orderStatisticsService;

    @GetMapping("/order/monthly-report")
    public ResponseEntity<List<OrderMonthlyReport>> getOrderMonthlyReport() {
        return ResponseEntity.ok(orderStatisticsService.getMonthlyReport());
    }
}
