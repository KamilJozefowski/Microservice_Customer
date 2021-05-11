package com.kodilla.customer.provider;

import com.kodilla.customer.connector.CardsConnector;
import com.kodilla.customer.domian.AccountDto;
import com.kodilla.customer.domian.CardsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CardsProvider {
    private final CardsConnector cardsConnector;

    public List<CardsDto> getCustomerCards(Long customerId) {
        return cardsConnector.getCards(customerId)
                .getCardsDtoList()
                .stream()
                .map(cards -> new CardsDto(
                        cards.getId(),
                        cards.getCustomerId(),
                        cards.getName(),
                        cards.getCreditValue(),
                        cards.getCardNumber()))
                .collect(Collectors.toList());
    }

}