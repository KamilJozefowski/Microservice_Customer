package com.kodilla.customer.service;

import com.kodilla.customer.domian.AccountDto;
import com.kodilla.customer.mapper.AccountsProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final AccountsProvider accountProvider;

    public List<AccountDto> findCustomerAccounts(Long customerId) {
        return accountProvider.getCustomerAccounts(customerId);
    }

}