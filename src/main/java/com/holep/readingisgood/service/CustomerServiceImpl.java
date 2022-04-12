package com.holep.readingisgood.service;

import com.holep.readingisgood.auth.util.PasswordGenerator;
import com.holep.readingisgood.data.dto.CustomerDTO;
import com.holep.readingisgood.data.entity.Customer;
import com.holep.readingisgood.data.mapper.CustomerEntityMapper;
import com.holep.readingisgood.data.repository.CustomerRepository;
import com.holep.readingisgood.exception.CustomerAlreadyExistsException;
import com.holep.readingisgood.exception.CustomerNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    final CustomerEntityMapper entityMapper;
    final CustomerRepository repository;

    final PasswordGenerator passwordGenerator;

    @Override
    public CustomerDTO create(CustomerDTO customerDTO) {

        Customer customer = entityMapper.toEntity(customerDTO);

        customer.setId(UUID.randomUUID());
        customer.setPassword(passwordGenerator.generate(customerDTO.getEmail()));

        // Check if email is already used before! Then save if possible.
        getByEmail(customer.getEmail())
                .ifPresentOrElse(customerDTO1 -> {
                    throw new CustomerAlreadyExistsException();
                }, () -> repository.save(customer));

        return entityMapper.toDTO(customer);
    }

    @Override
    public CustomerDTO getById(UUID id) {
        return repository
                .findById(id)
                .map(entityMapper::toDTO)
                .orElseThrow(CustomerNotFoundException::new);

    }

    @Override
    public Optional<CustomerDTO> getByEmail(String email) {
        return repository
                .findByEmail(email).map(entityMapper::toDTO);
    }
}
