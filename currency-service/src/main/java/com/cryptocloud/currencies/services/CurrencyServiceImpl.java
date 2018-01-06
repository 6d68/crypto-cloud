package com.cryptocloud.currencies.services;

import com.cryptocloud.currencies.model.Currency;
import com.cryptocloud.currencies.repositories.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CurrencyServiceImpl implements CurrencyService {

    private final CurrencyRepository currencyRepository;

    @Autowired
    public CurrencyServiceImpl(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    public List<Currency> getCurrencies() {
        return currencyRepository.findAll();
    }

    public Currency getCurrency(String currencyId) {
        return currencyRepository.findOne(currencyId);
    }

    public void saveCurrency(Currency currency){
        currencyRepository.save(currency);

    }
}