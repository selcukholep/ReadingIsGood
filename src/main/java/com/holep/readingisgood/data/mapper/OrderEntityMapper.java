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
                .id(order.getId())
                .status(order.getStatus())
                .customerId(order.getCustomerId())
                .creationDate(order.getCreationDate())
                .details(order.getDetails())
                .build();
    }

    @Override
    public Order toEntity(OrderDTO orderDTO) {
        return Order.builder()
                .details(orderDTO.getDetails())
                .build();
    }
}
