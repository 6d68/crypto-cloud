package com.cryptocloud.currencies.services;

import com.cryptocloud.currencies.ItemNotFoundException;
import com.cryptocloud.currencies.model.Currency;
import java.util.List;


public interface CurrencyService {

    List<Currency> getCurrencies();

    Currency getCurrency(String currencyId) throws ItemNotFoundException;

    List<Currency>  saveCurrencies(Iterable<Currency> currencies);
}