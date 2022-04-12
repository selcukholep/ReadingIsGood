package com.holep.readingisgood.service;

import com.holep.readingisgood.data.constant.OrderStatus;
import com.holep.readingisgood.data.dto.BookDTO;
import com.holep.readingisgood.data.dto.CustomerDTO;
import com.holep.readingisgood.data.dto.OrderDTO;
import com.holep.readingisgood.data.entity.Order;
import com.holep.readingisgood.data.mapper.OrderEntityMapper;
import com.holep.readingisgood.data.repository.OrderRepository;
import com.holep.readingisgood.exception.NotEnoughStockException;
import com.holep.readingisgood.exception.OrderNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    final OrderRepository repository;
    final OrderEntityMapper mapper;

    final BookService bookService;
    final CustomerService customerService;

    @Override
    public OrderDTO getById(UUID id) {
        return repository.findById(id)
                .map(mapper::toDTO)
                .orElseThrow(OrderNotFoundException::new);
    }

    @Transactional
    @Override
    public OrderDTO create(OrderDTO orderDTO, CustomerDTO customerDTO) {

        BookDTO bookDTO = bookService.getById(UUID.fromString(orderDTO.getBookId()));
        Order order = mapper.toEntity(orderDTO);

        checkStock(bookDTO, order);
        decrementAndUpdateStock(bookDTO, order);

        populateOrderModel(customerDTO, bookDTO, order);

        return mapper.toDTO(repository.save(order));
    }

    @Override
    public Page<OrderDTO> getAllByCustomerId(UUID customerId, Pageable pageable) {
        return repository.findAllByCustomerId(customerId, pageable)
                .map(mapper::toDTO);
    }

    @Override
    public Page<OrderDTO> getAllByCreationDateBetween(Date startDate, Date endDate, Pageable pageable) {
        return repository.findAllByCreationDateBetween(startDate, endDate, pageable)
                .map(mapper::toDTO);
    }

    // Private Methods

    private void checkStock(BookDTO bookDTO, Order order) {
        if (!bookService.isStockEnough(bookDTO, order.getAmount())) {
            throw new NotEnoughStockException();
        }
    }

    private void decrementAndUpdateStock(BookDTO bookDTO, Order order) {
        bookService.updateStock(bookDTO, bookDTO.getStock() - order.getAmount());
    }

    private void populateOrderModel(CustomerDTO customerDTO, BookDTO bookDTO, Order order) {
        order.setId(UUID.randomUUID());
        order.setCustomerId(UUID.fromString(customerDTO.getId()));
        order.setStatus(OrderStatus.PENDING);
        order.setCreationDate(new Date());
        order.setPrice(order.getAmount() * bookDTO.getPrice());
    }
}
