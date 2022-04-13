package com.holep.readingisgood.data.mapper;

import com.holep.readingisgood.data.dto.CustomerDTO;
import com.holep.readingisgood.data.entity.Customer;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component("mapper-customer")
public class CustomerEntityMapper implements EntityMapper<Customer, CustomerDTO> {

    @Override
    public CustomerDTO toDTO(Customer customer) {
        return CustomerDTO.builder()
                .id(customer.getId())
                .name(customer.getName())
                .surname(customer.getSurname())
                .email(customer.getEmail())
                .password(customer.getPassword())
                .build();
    }

    @Override
    public Customer toEntity(CustomerDTO customerDTO) {
        return Customer.builder()
                .name(customerDTO.getName())
                .surname(customerDTO.getSurname())
                .email(customerDTO.getEmail())
                .build();
    }
}
