package com.kodilla.customer.domian;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAccountsResponse {
    private List<AccountDto> accountDtoList;
}
