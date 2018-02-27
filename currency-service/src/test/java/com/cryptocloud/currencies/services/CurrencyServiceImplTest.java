package com.cryptocloud.currencies.services;

import com.cryptocloud.currencies.ItemNotFoundException;
import com.cryptocloud.currencies.model.Currency;
import com.cryptocloud.currencies.repositories.CurrencyRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Instant;
import java.util.Arrays;
import java.util.Date;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class CurrencyServiceImplTest {
    @TestConfiguration
    static class CurrencyServiceImplTestContextConfiguration {

        @Autowired
        private CurrencyRepository currencyRepository;

        @Bean
        public CurrencyService currencyService() {
            return new CurrencyServiceImpl(currencyRepository);
        }
    }

    @Autowired
    private CurrencyService currencyService;

    @MockBean
    private CurrencyRepository currencyRepository;

    @Test
    public void whenValidId_thenCurrencyShouldBeFound() {

        Currency eth = new Currency("ETH", "ETH", "ETH", Date.from(Instant.now()), 1.1, 900.32, "EUR", 1.5, 2.1);
        Mockito.when(currencyRepository.findOne(eth.getName())).thenReturn(eth);
        
        Currency found = currencyService.getCurrency(eth.getName());

        assertThat(found.getName(), is(eth.getName()));
    }

    @Test(expected = ItemNotFoundException.class)
    public void whenInvalidId_thenItemNotFoundExceptionShouldBeThrown() {
        String invalidId = "invalid";

        Mockito.when(currencyRepository.findOne(invalidId)).thenReturn(null);
        currencyService.getCurrency(invalidId);
    }


    @Test
    public void whenSavingListofCurrencies_thenCurrenciesShouldBeSaved() {
        Currency eth = new Currency("ETH", "ETH", "ETH", Date.from(Instant.now()), 1.1, 900.32, "EUR", 1.5, 2.1);
        Currency btc = new Currency("BTC", "BTC", "BTC", Date.from(Instant.now()), 1.1, 900.32, "EUR", 1.5, 2.1);

        Iterable<Currency> currencies = Arrays.asList(eth, btc);

        currencyService.saveCurrencies(currencies);

        verify(currencyRepository, times(1)).save(currencies);
    }
}