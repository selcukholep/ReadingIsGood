package com.holep.readingisgood.service;

import com.holep.readingisgood.auth.util.PasswordGenerator;
import com.holep.readingisgood.data.dto.CustomerDTO;
import com.holep.readingisgood.data.entity.Customer;
import com.holep.readingisgood.data.mapper.CustomerEntityMapper;
import com.holep.readingisgood.data.repository.CustomerRepository;
import com.holep.readingisgood.exception.CustomerAlreadyExistsException;
import com.holep.readingisgood.exception.CustomerNotFoundException;
import com.holep.readingisgood.service.impl.CustomerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;
import java.util.UUID;

import static com.mongodb.assertions.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.when;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTests {

    @InjectMocks
    CustomerServiceImpl customerService;

    @Mock
    CustomerRepository customerRepository;

    @Spy
    CustomerEntityMapper customerEntityMapper;

    @Spy
    PasswordGenerator passwordGenerator = new PasswordGenerator(new BCryptPasswordEncoder());

    private static CustomerDTO customerDTO;
    private static Customer customer;

    @BeforeEach
    public void init() {
        customerDTO = CustomerDTO.builder()
                .email("test@test.com")
                .name("test")
                .surname("test")
                .build();

        customer = Customer.builder()
                .email("test2@test.com")
                .name("test2")
                .surname("test2")
                .id(UUID.fromString("8f4a0e56-012b-46c6-a29c-052425468f7c"))
                .password("$2a$10$YYByPtnRbCieu0elk9z6ee7Es6TVOpl4Cn3Wp9/v9EkWxfOxnFa26")
                .build();
    }

    @Test
    public void persistConsumer_whenEmailAlreadyExists_thenThrowCustomerAlreadyException() {

        when(customerRepository.findByEmail(customerDTO.getEmail()))
                .thenReturn(Optional.of(customer));

        assertThrows(CustomerAlreadyExistsException.class, () -> customerService.create(customerDTO));
    }

    @Test
    public void persistConsumer_whenEmailNotExistsAndParametersFullyFilled_thenReturnSuccessResponse() {
        when(customerRepository.findByEmail(customerDTO.getEmail()))
                .thenReturn(Optional.empty());

        CustomerDTO createdCustomerDTO = customerService.create(customerDTO);

        assertNotNull(createdCustomerDTO);
        assertEquals(customerDTO.getEmail(), createdCustomerDTO.getEmail());
    }

    @Test
    public void getConsumer_whenCustomerNotFound_thenThrowCustomerNotFoundException() {
        when(customerRepository.findById(customerDTO.getId()))
                .thenReturn(Optional.empty());

        assertThrows(CustomerNotFoundException.class, () -> customerService.getById(customerDTO.getId()));
    }
}
