package com.holep.readingisgood.controller;

import com.holep.readingisgood.data.dto.CustomerDTO;
import com.holep.readingisgood.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getById(
            @PathVariable String id
    ) {
        return ResponseEntity.ok(customerService.getById(UUID.fromString(id)));
    }
}
