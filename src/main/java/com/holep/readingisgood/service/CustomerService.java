package com.holep.readingisgood.service;

import com.holep.readingisgood.data.dto.CustomerDTO;

import java.util.Optional;
import java.util.UUID;

public interface CustomerService {

    CustomerDTO create(CustomerDTO customerDTO);
    CustomerDTO getById(UUID id);
    Optional<CustomerDTO> getByEmail(String email);
}
