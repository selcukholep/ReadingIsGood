package com.holep.readingisgood.controller;

import com.holep.readingisgood.auth.data.AuthUser;
import com.holep.readingisgood.data.dto.CustomerDTO;
import com.holep.readingisgood.data.dto.OrderDTO;
import com.holep.readingisgood.domain.request.DateIntervalRequest;
import com.holep.readingisgood.domain.request.PaginationRequest;
import com.holep.readingisgood.service.CustomerService;
import com.holep.readingisgood.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController implements BaseController {

    final OrderService orderService;
    final CustomerService customerService;

    @PreAuthorize("hasRole('CUSTOMER')")
    @PostMapping(consumes = "application/json")
    public ResponseEntity<OrderDTO> create(
            @RequestBody @Valid OrderDTO orderDTO,
            @AuthenticationPrincipal AuthUser authUser) {

        CustomerDTO customerDTO = customerService.getById(authUser.getId());

        return new ResponseEntity<>(orderService.create(orderDTO, customerDTO), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getById(
            @PathVariable String id) {
        return ResponseEntity.ok(orderService.getById(UUID.fromString(id)));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/search")
    public ResponseEntity<Page<OrderDTO>> filterByCreationDate(
            @Valid DateIntervalRequest dateIntervalRequest,
            @Valid PaginationRequest paginationRequest) {
        return ResponseEntity.ok(orderService.getAllByCreationDateBetween(dateIntervalRequest, paginationRequest));
    }
}
