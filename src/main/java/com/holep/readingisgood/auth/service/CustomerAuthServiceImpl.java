package com.holep.readingisgood.auth.service;

import com.holep.readingisgood.auth.data.AuthUser;
import com.holep.readingisgood.auth.util.AuthType;
import com.holep.readingisgood.data.dto.CustomerDTO;
import com.holep.readingisgood.service.CustomerService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service("auth-customer-service")
public class CustomerAuthServiceImpl implements AuthService {

    final CustomerService customerService;

    public CustomerAuthServiceImpl(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    public AuthType authType() {
        return AuthType.CUSTOMER;
    }

    @Override
    public AuthUser loadUserByUsername(String username) throws UsernameNotFoundException {

        CustomerDTO customerDTO = customerService.getByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Bad credentials."));

        return generateAuthUser(customerDTO);
    }

    private AuthUser generateAuthUser(CustomerDTO customerDTO) {
        AuthUser authUser = new AuthUser();

        authUser.setId(customerDTO.getId());
        authUser.setUsername(customerDTO.getEmail());
        authUser.setPassword(customerDTO.getPassword());
        authUser.setAuthType(AuthType.CUSTOMER);

        return authUser;
    }
}
