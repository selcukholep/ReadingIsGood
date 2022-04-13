package com.holep.readingisgood.service.impl;

import com.holep.readingisgood.data.constant.OrderStatus;
import com.holep.readingisgood.data.dto.CustomerDTO;
import com.holep.readingisgood.data.dto.OrderDTO;
import com.holep.readingisgood.data.entity.Order;
import com.holep.readingisgood.data.mapper.OrderEntityMapper;
import com.holep.readingisgood.data.repository.OrderRepository;
import com.holep.readingisgood.domian.DateIntervalRequest;
import com.holep.readingisgood.domian.PaginationRequest;
import com.holep.readingisgood.exception.OrderNotFoundException;
import com.holep.readingisgood.service.OrderDetailService;
import com.holep.readingisgood.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    final OrderRepository repository;
    final OrderEntityMapper mapper;

    final OrderDetailService orderDetailService;

    @Override
    public OrderDTO getById(UUID id) {
        return repository.findById(id)
                .map(mapper::toDTO)
                .orElseThrow(OrderNotFoundException::new);
    }

    @Override
    public Page<OrderDTO> getAllByCustomerId(UUID customerId, PaginationRequest paginationRequest) {
        return repository.findAllByCustomerId(customerId,
                        PageRequest.of(paginationRequest.getPage(), paginationRequest.getSize()))
                .map(mapper::toDTO);
    }

    @Override
    public Page<OrderDTO> getAllByCreationDateBetween(DateIntervalRequest dateIntervalRequest, PaginationRequest paginationRequest) {
        return repository.findAllByCreationDateBetween(dateIntervalRequest.getStartDate(), dateIntervalRequest.getEndDate(),
                        PageRequest.of(paginationRequest.getPage(), paginationRequest.getSize()))
                .map(mapper::toDTO);
    }

    @Transactional
    @Override
    public OrderDTO create(OrderDTO orderDTO, CustomerDTO customerDTO) {

        Order order = mapper.toEntity(orderDTO);

        orderDTO.getDetails()
                        .forEach(orderDetailService::create);

        populateOrderModel(order, customerDTO);

        return mapper.toDTO(repository.save(order));
    }

    // Private Methods

    private void populateOrderModel(Order order, CustomerDTO customerDTO) {
        order.setId(UUID.randomUUID());
        order.setCustomerId(customerDTO.getId());
        order.setStatus(OrderStatus.PENDING);
        order.setCreationDate(new Date());
    }
}
