package com.kodilla.customer.service;


import com.kodilla.customer.domian.Customer;
import com.kodilla.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DbService {
    private final CustomerRepository customerRepository;

    public Optional<Customer> getCustomer(final Long taskId) {
        return customerRepository.findById(taskId);
    }
}
