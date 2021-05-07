package com.kodilla.customer.controller;

import com.kodilla.customer.domian.CustomerDto;
import com.kodilla.customer.mapper.CustomerMapper;
import com.kodilla.customer.service.DbService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RefreshScope
@Slf4j
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class CustomerController {

    @Value("${application.allow-get-customer}")
    private boolean allowGetCustomer;

    private final DbService dbService;
    private final CustomerMapper customerMapper;

    @RequestMapping(method = RequestMethod.GET, value = "/customer/{customerId}")
    public CustomerDto getCustomer(@PathVariable Long customerId) throws TaskNotFoundException {
        if(!allowGetCustomer){
            log.info("Getting customer is disabled.");
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Getting customer is disabled.");
        }
        return customerMapper.mapToCustomerDto(dbService.getCustomer(customerId).orElseThrow(TaskNotFoundException::new));
        //return new CustomerDto(1L,"Tom","last");
    }
}
