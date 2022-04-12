package com.holep.readingisgood.data.mapper;

import com.holep.readingisgood.data.dto.OrderDTO;
import com.holep.readingisgood.data.entity.Order;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component("mapper-order")
public class OrderEntityMapper implements EntityMapper<Order, OrderDTO> {

    @Override
    public OrderDTO toDTO(Order order) {
        return OrderDTO.builder()
                .id(order.getId().toString())
                .amount(order.getAmount())
                .status(order.getStatus())
                .bookId(order.getBookId().toString())
                .customerId(order.getCustomerId().toString())
                .creationDate(order.getCreationDate())
                .price(order.getPrice())
                .build();
    }

    @Override
    public Order toEntity(OrderDTO orderDTO) {
        return Order.builder()
                .amount(orderDTO.getAmount())
                .bookId(UUID.fromString(orderDTO.getBookId()))
                .build();
    }
}
