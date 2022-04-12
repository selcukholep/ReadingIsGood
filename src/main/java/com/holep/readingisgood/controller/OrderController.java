package com.holep.readingisgood.controller;

import com.holep.readingisgood.auth.data.AuthUser;
import com.holep.readingisgood.data.dto.CustomerDTO;
import com.holep.readingisgood.data.dto.OrderDTO;
import com.holep.readingisgood.service.CustomerService;
import com.holep.readingisgood.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    final OrderService orderService;
    final CustomerService customerService;

    @PreAuthorize("hasRole('CUSTOMER')")
    @PostMapping
    public ResponseEntity<OrderDTO> create(
            @Valid OrderDTO orderDTO,
            @AuthenticationPrincipal AuthUser authUser) {

        CustomerDTO customerDTO = customerService.getById(authUser.getId());

        return ResponseEntity.ok(orderService.create(orderDTO, customerDTO));
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
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate,
            @RequestParam(defaultValue = "10") @Min(1) int size,
            @RequestParam(defaultValue = "0") @Min(1) int page) {
        return ResponseEntity.ok(orderService.getAllByCreationDateBetween(startDate, endDate, PageRequest.of(page, size)));
    }
}
