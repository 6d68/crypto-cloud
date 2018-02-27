package com.cryptocloud.currencies.services;

import com.cryptocloud.currencies.ItemNotFoundException;
import com.cryptocloud.currencies.model.Currency;
import com.cryptocloud.currencies.repositories.CurrencyRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CurrencyServiceImpl implements CurrencyService {

    private final CurrencyRepository currencyRepository;

    @Autowired
    public CurrencyServiceImpl(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    @Override
    public Page<Currency> pagedCurrencies(Pageable pageable) {
        return currencyRepository.findAll(pageable);
    }

    public Currency getCurrency(String currencyId) throws ItemNotFoundException {
        Currency currency = currencyRepository.findOne(currencyId);
        if(currency == null) throw new ItemNotFoundException(String.format("Item with id %s not found!", currencyId));
        
        return currency;
    }

    public List<Currency> saveCurrencies(Iterable<Currency> currencies){
        return currencyRepository.save(currencies);
    }
}