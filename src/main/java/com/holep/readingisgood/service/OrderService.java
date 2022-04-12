package com.holep.readingisgood.service;

import com.holep.readingisgood.data.dto.CustomerDTO;
import com.holep.readingisgood.data.dto.OrderDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.UUID;

public interface OrderService {

    OrderDTO getById(UUID id);

    OrderDTO create(OrderDTO orderDTO, CustomerDTO customerDTO);

    Page<OrderDTO> getAllByCustomerId(UUID customerId, Pageable pageable);
    Page<OrderDTO> getAllByCreationDateBetween(Date startDate, Date endDate, Pageable pageable);
}
