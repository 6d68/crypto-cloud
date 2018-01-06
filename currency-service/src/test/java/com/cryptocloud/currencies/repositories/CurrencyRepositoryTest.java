package com.cryptocloud.currencies.repositories;

import com.cryptocloud.currencies.model.Currency;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataMongoTest
public class CurrencyRepositoryTest {

    @Autowired
    CurrencyRepository currencyRepository;

    private static final int QTY = 20;

    @Before
    public void init() {
        currencyRepository.deleteAll();
        for (int i = 0; i < QTY; i++) {

            Currency eth = new Currency("ETH" + i, "ETH", "ETH", Date.from(Instant.now()), 1.1, 900.32, "EUR", 1.5, 2.1);
            currencyRepository.save(eth);
        }
    }

    @Test
    public void whenAllCurrenciesRequested_thenAllCurrenciesShouldBeReturned() {
        List<Currency> list = currencyRepository.findAll();
        assertEquals(QTY, list.size());
    }
}