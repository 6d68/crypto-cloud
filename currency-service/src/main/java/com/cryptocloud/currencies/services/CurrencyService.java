package com.cryptocloud.currencies.services;

import com.cryptocloud.currencies.model.Currency;
import java.util.List;


public interface CurrencyService {

    List<Currency> getCurrencies();

    Currency getCurrency(String currencyId);

    void saveCurrency(Currency currency);

    void saveCurrencies(Iterable<Currency> currencies);
}