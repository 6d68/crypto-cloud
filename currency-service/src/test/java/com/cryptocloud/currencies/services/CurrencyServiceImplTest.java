package com.cryptocloud.currencies.services;

import com.cryptocloud.currencies.model.Currency;
import com.cryptocloud.currencies.repositories.CurrencyRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Instant;
import java.util.Date;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

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

    @Before
    public void setUp() {
        Currency eth = new Currency("ETH", "ETH", "ETH", Date.from(Instant.now()), 1.1, 900.32, "EUR", 1.5, 2.1);

        Mockito.when(currencyRepository.findOne(eth.getName())).thenReturn(eth);
    }

    @Test
    public void whenValidId_thenCurrencyShouldBeFound() {
        String name = "ETH";
        Currency found = currencyService.getCurrency(name);

        assertThat(found.getName(), is(name));
    }
}