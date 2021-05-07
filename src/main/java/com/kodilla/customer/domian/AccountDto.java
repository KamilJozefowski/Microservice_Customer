package com.kodilla.customer.domian;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccountDto {

    private Long id;
    private String nrb;
    private String currency;
    private Double availableFunds;
}
