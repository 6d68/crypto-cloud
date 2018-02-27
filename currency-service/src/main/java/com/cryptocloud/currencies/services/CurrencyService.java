package com.cryptocloud.currencies.services;

import com.cryptocloud.currencies.ItemNotFoundException;
import com.cryptocloud.currencies.model.Currency;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface CurrencyService {

    Page<Currency> pagedCurrencies(Pageable pageable);

    Currency getCurrency(String currencyId) throws ItemNotFoundException;

    List<Currency>  saveCurrencies(Iterable<Currency> currencies);
}