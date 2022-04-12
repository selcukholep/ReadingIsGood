package com.holep.readingisgood.service;

import com.holep.readingisgood.data.dto.CustomerDTO;
import com.holep.readingisgood.data.dto.OrderDTO;
import com.holep.readingisgood.domian.DateIntervalRequest;
import com.holep.readingisgood.domian.PaginationRequest;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface OrderService {

    OrderDTO getById(UUID id);

    OrderDTO create(OrderDTO orderDTO, CustomerDTO customerDTO);

    Page<OrderDTO> getAllByCustomerId(UUID customerId, PaginationRequest paginationRequest);
    Page<OrderDTO> getAllByCreationDateBetween(DateIntervalRequest dateIntervalRequest, PaginationRequest paginationRequest);
}
