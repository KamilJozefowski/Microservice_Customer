package com.kodilla.customer.domian;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class CardsDto {
    private Long id;
    private Long customerId;
    private String name;
    private Double creditValue;
    private String cardNumber;
}
