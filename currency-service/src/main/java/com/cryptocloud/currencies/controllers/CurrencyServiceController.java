package com.cryptocloud.currencies.controllers;

import com.cryptocloud.currencies.ItemNotFoundException;
import com.cryptocloud.currencies.model.Currency;
import com.cryptocloud.currencies.services.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value="/currencies")
public class CurrencyServiceController {

    private final CurrencyService currencyService;

    @Autowired
    public CurrencyServiceController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }


    @GetMapping(value="")
    public List<Currency> getCurrencies() {

        return currencyService.getCurrencies();
    }

    @GetMapping(value="/{currencyId}")
    public Currency getCurrency(@PathVariable("currencyId") String currencyId) throws ItemNotFoundException{

        return currencyService.getCurrency(currencyId);
    }

    @PostMapping(value="")
    public List<Currency> saveCurrencies(@RequestBody Iterable<Currency> currencies) {

        return currencyService.saveCurrencies(currencies);
    }
}
