package com.holep.readingisgood.service;

import com.holep.readingisgood.data.dto.CustomerDTO;
import com.holep.readingisgood.data.entity.Customer;
import com.holep.readingisgood.data.mapper.CustomerEntityMapper;
import com.holep.readingisgood.data.repository.CustomerRepository;
import com.holep.readingisgood.exception.CustomerNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CustomerServiceImpl implements CustomerService {

    final CustomerEntityMapper entityMapper;
    final CustomerRepository repository;

    public CustomerServiceImpl(CustomerEntityMapper entityMapper,
                               CustomerRepository repository) {
        this.entityMapper = entityMapper;
        this.repository = repository;
    }

    @Override
    public CustomerDTO create(CustomerDTO customerDTO) {

        Customer customer = entityMapper.toEntity(customerDTO);

        customer.setId(UUID.randomUUID());

        repository.save(customer);

        return entityMapper.toDTO(customer);
    }

    @Override
    public CustomerDTO getById(UUID id) {
        return entityMapper.toDTO(
                repository
                        .findById(id)
                        .orElseThrow(CustomerNotFoundException::new)
        );
    }

    @Override
    public CustomerDTO getByEmail(String email) {
        return entityMapper.toDTO(
                repository
                        .findByEmail(email)
                        .orElseThrow(CustomerNotFoundException::new)
        );
    }
}
