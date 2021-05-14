package com.kodilla.customer.controller;

import com.kodilla.customer.domian.AccountDto;
import com.kodilla.customer.domian.CardsDto;
import com.kodilla.customer.domian.CustomerDto;
import com.kodilla.customer.domian.GetCustomerProductsResponse;
import com.kodilla.customer.mapper.CustomerMapper;
import com.kodilla.customer.provider.AccountsProvider;
import com.kodilla.customer.provider.CardsProvider;
import com.kodilla.customer.service.DbService;
import com.kodilla.customer.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RefreshScope
@Slf4j
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/v1/customer", produces = {MediaType.APPLICATION_JSON_VALUE})
@RequiredArgsConstructor
public class CustomerController {

    private final DbService dbService;
    private final CustomerMapper customerMapper;
    private final ProductService productService;
    private final AccountsProvider accountsProvider;
    private final CardsProvider cardsProvider;
    @Value("${application.allow-get-customer}")
    private boolean allowGetCustomer;

    @RequestMapping(method = RequestMethod.GET, value = "/{customerId}")
    public CustomerDto getCustomer(@PathVariable Long customerId) throws TaskNotFoundException {
        if (!allowGetCustomer) {
            log.info("Getting customer is disabled.");
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Getting customer is disabled.");
        }
        return customerMapper.mapToCustomerDto(dbService.getCustomer(customerId).orElseThrow(TaskNotFoundException::new));
    }

    @GetMapping("/{customerId}/products")
    public GetCustomerProductsResponse getCustomerProducts(@PathVariable Long customerId) throws TaskNotFoundException {
        CustomerDto customerDto = customerMapper.mapToCustomerDto(dbService.getCustomer(customerId).orElseThrow(TaskNotFoundException::new));

        List<AccountDto> customerAccounts = accountsProvider.getCustomerAccounts(customerId);
        List<CardsDto> cardsDto = cardsProvider.getCustomerCards(customerId);

        return GetCustomerProductsResponse.builder()
                .customerId(customerDto.getId())
                .fullName(customerDto.getFirstName() + " " + customerDto.getLastName())
                .accounts(customerAccounts)
                .cards(cardsDto)
                .build();
    }


}
