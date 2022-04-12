package com.holep.readingisgood.controller;

import com.holep.readingisgood.data.dto.CustomerDTO;
import com.holep.readingisgood.data.dto.OrderDTO;
import com.holep.readingisgood.service.CustomerService;
import com.holep.readingisgood.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.UUID;

@RestController
@RequestMapping("/customer")
@PreAuthorize("hasRole('ADMIN')")
@RequiredArgsConstructor
public class CustomerController {

    final CustomerService customerService;
    final OrderService orderService;

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getById(
            @PathVariable String id) {
        return ResponseEntity.ok(customerService.getById(UUID.fromString(id)));
    }

    @PostMapping
    public ResponseEntity<CustomerDTO> create(
            @Valid CustomerDTO customerDTO) {
        return new ResponseEntity<>(customerService.create(customerDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{id}/orders")
    public ResponseEntity<Page<OrderDTO>> getAllOrders(
            @PathVariable String id,
            @RequestParam(defaultValue = "10") @Min(1) int size,
            @RequestParam(defaultValue = "0") @Min(1) int page) {

        CustomerDTO customerDTO = customerService.getById(UUID.fromString(id));

        return ResponseEntity.ok(orderService.getAllByCustomerId(UUID.fromString(customerDTO.getId()),
                PageRequest.of(page, size)));
    }
}
